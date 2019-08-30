package com.dolphinpay.server.rest_api.v1.orders_products;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStates;
import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStatesService;
import com.dolphinpay.server.rest_api.v1.users.UserService;
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

import java.util.Optional;

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
    private UserService userService;
    @NonNull
    private OrdersStatesService ordersStatesService;

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


    @GetMapping(UtilsV1.URLS.ordersProducts)
    @ApiOperation(
            value = "Get specific order product"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getSpecificOrderProduct(
            @RequestParam String token,
            @PathVariable("id") Integer ordersProductsId) {
        return checkAuthAndUser(userService, token, (user) -> ResponseEntity.ok(service.findById(ordersProductsId)));
    }


    @Transactional
    @PostMapping(UtilsV1.URLS.ordersProductsState)
    @ApiOperation(
            value = "Change orders products state and notify"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity changeOrdersProductsState(
            @RequestParam String token,
            @PathVariable("id") Integer ordersProductsId,
            @RequestBody Integer stateId) {
        return checkAuthAndUser(userService, token, (user) -> {
            Optional<OrdersProducts> op = service.findById(ordersProductsId);

            if (!op.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Optional<OrdersStates> ordersStates = ordersStatesService.findById(stateId);

            if (!ordersStates.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            op.get().setState(ordersStates.get());
            service.save(op.get());
            notifyStateChanged(op.get());

            return ResponseEntity.ok(op.get());
        });
    }


    private void notifyStateChanged(OrdersProducts ordersProducts) {
        // TODO

    }

}
