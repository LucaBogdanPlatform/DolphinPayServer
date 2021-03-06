package com.dolphinpay.server.rest_api.v1.orders_products;

import com.dolphinpay.server.rest_api.utils.FirebaseUtils;
import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONTimestamp;
import com.dolphinpay.server.rest_api.v1.orders.Orders;
import com.dolphinpay.server.rest_api.v1.orders.OrdersService;
import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStates;
import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStatesService;
import com.dolphinpay.server.rest_api.v1.products.Products;
import com.dolphinpay.server.rest_api.v1.products.ProductsService;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevices;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevicesService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.concurrent.ExecutionException;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class OrdersProductsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(OrdersProductsAPI.class);

    @NonNull
    private OrdersProductsService service;
    @NonNull
    private OrdersService ordersService;
    @NonNull
    private ProductsService productsService;
    @NonNull
    private UserService userService;
    @NonNull
    private OrdersStatesService ordersStatesService;
    @NonNull
    private UsersDevicesService usersDevicesService;


    @GetMapping(UtilsV1.URLS.roomOrders)
    @ApiOperation(
            value = "Get all products parts of pending ordinations, filtered by room"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getAllPlatforms(
            @RequestParam String token,
            @PathVariable("id") Integer roomId) { // TODO paging
        return checkAuthAndUser(userService, token, (user) -> {
            OrdersProducts[] standsRooms = service.findByRoomIdNotClosed(roomId);
            return ResponseEntity.ok(standsRooms);
        });
    }


//    @GetMapping(UtilsV1.URLS.ordersProducts)
//    @ApiOperation(
//            value = "Get specific order product"
//    )
//    @ApiResponses(
//            value = {
//                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
//                    @ApiResponse(code = 401, message = "Token elapsed time")
//            }
//    )
//    public ResponseEntity getSpecificOrderProduct(
//            @RequestParam String token,
//            @PathVariable("id") Integer ordersProductsId) {
//        return checkAuthAndUser(userService, token, (user) -> ResponseEntity.ok(service.findById(ordersProductsId)));
//    }


    @Transactional
    @PostMapping(UtilsV1.URLS.ordersProductsStateReady)
    @ApiOperation(
            value = "Change orders products state to ready and notify"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity closeOrderProduct(
            @RequestParam String token,
            @PathVariable("orderId") Integer orderId,
            @PathVariable("productId") Integer productId,
            @RequestBody JSONTimestamp closureTime) {
        return checkAuthAndUser(userService, token, (user) -> {
            Optional<OrdersProducts> op = service.findById(new OrdersProductsIds(orderId, productId));

            if (!op.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Optional<OrdersStates> ordersStates = ordersStatesService.findById(OrdersProducts.StatesIds.STATE_READY.state);

            if (!ordersStates.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            op.get().setOfficialClosureTime(new Date(closureTime.getClosureTime()));
            op.get().setState(ordersStatesService.findById(3).get());
            service.save(op.get());

            int countProductsOpenOfOrder = ordersService.countProductsToPrepareOfOrder(orderId);

            if (countProductsOpenOfOrder == 0) {
                Optional<Orders> ord = ordersService.findById(orderId);
                if (!ord.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }

                ord.get().setOfficialClosureTime(op.get().getOfficialClosureTime());
                ord.get().setState(op.get().getState());
                ordersService.save(ord.get());

                try {
                    FirebaseUtils.sendOrderReady(usersDevicesService.findByUser(ord.get().getUser()), ord.get());
                } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
                    e.printStackTrace(); // TODO HANDLE IT
                }
            }

            Optional<Products> p = productsService.findById(op.get().getIds().getProduct());
            UsersDevices[] devicesToSendOrderProduct =
                    usersDevicesService.findAllObservingWithPartnershipObservingCategory(
                            p.get().getStand().getId(),
                            p.get().getType().getCategory().getId()
                    );

            try {
                FirebaseUtils.sendOrderProductClosed(devicesToSendOrderProduct, op.get(), p.get());
            } catch (JsonProcessingException | InterruptedException | ExecutionException e) {
                e.printStackTrace(); // TODO HANDLE IT
            }
            return ResponseEntity.ok(op.get());
        });
    }

}
