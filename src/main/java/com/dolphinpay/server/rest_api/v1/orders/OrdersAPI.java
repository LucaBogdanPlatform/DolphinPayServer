package com.dolphinpay.server.rest_api.v1.orders;

import com.dolphinpay.server.rest_api.utils.FirebaseUtils;
import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONNewOrder;
import com.dolphinpay.server.rest_api.v1.orders_products.OrdersProducts;
import com.dolphinpay.server.rest_api.v1.orders_products.OrdersProductsIds;
import com.dolphinpay.server.rest_api.v1.orders_products.OrdersProductsService;
import com.dolphinpay.server.rest_api.v1.products.Products;
import com.dolphinpay.server.rest_api.v1.products.ProductsService;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevices;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevicesService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class OrdersAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrdersAPI.class);

    @NonNull
    private UserService userService;
    @NonNull
    private OrdersService service;
    @NonNull
    private OrdersProductsService ordersProductsService;
    @NonNull
    private ProductsService productsService;
    @NonNull
    private UsersDevicesService usersDevicesService;

    @Transactional
    @PostMapping(UtilsV1.URLS.standOrders)
    @ApiOperation(
            value = "Create new order"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity newOrder(
            @RequestParam String token,
            @PathVariable("id") Integer standId,
            @RequestBody JSONNewOrder newOrder) {
        return checkAuthAndUser(userService, token, (user) -> {

            int pendingOrder = service.countStandOpenOrders(standId);
            int minutesToWait = (pendingOrder * 30) / 100;

            Date endPrepareTime = new Date(new Date().getTime() + 60000 * minutesToWait);
            Date startPrepareTime = new Date();


            Orders order = service.save(Orders.builder().user(user).expectedEndTime(endPrepareTime).build());

            for (JSONNewOrder.JSONPair p : newOrder.getProducts()) {
                Optional<Products> pr = productsService.findById(p.getProductId());
                if (!pr.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
                OrdersProducts ordersProducts = ordersProductsService.save(
                        OrdersProducts.builder()
                                .expectedStartTime(startPrepareTime)
                                .expectedEndTime(endPrepareTime)
                                .ids(OrdersProductsIds.builder().order(order.getId()).product(pr.get().getId()).build())
                                .quantity(p.getProductQuantity())
                                .build()
                );

                UsersDevices[] devicesToSendOrderProduct =
                        usersDevicesService.findAllObservingWithPartnershipObservingCategory(
                                standId, pr.get().getType().getCategory().getId()
                        );

                try {
                    FirebaseUtils.sendOrdersProducts(devicesToSendOrderProduct, ordersProducts);
                } catch (Exception e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }

            return ResponseEntity.ok().build();
        });
    }


}
