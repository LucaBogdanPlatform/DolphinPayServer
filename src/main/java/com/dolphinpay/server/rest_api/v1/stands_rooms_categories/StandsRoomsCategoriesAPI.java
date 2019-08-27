package com.dolphinpay.server.rest_api.v1.stands_rooms_categories;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONIntegerPair;
import com.dolphinpay.server.rest_api.v1.products_types_categories.ProductsTypesCategories;
import com.dolphinpay.server.rest_api.v1.products_types_categories.ProductsTypesCategoriesService;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRooms;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRoomsService;
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
public class StandsRoomsCategoriesAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StandsRoomsCategoriesAPI.class);

    @NonNull
    private StandsRoomsCategoriesService service;
    @NonNull
    private StandsRoomsService standsRoomsService;
    @NonNull
    private ProductsTypesCategoriesService productsTypesCategoriesService;


    @NonNull
    private UserService userService;


    @Transactional
    @DeleteMapping(UtilsV1.URLS.deleteCategoryFromRoom)
    @ApiOperation(
            value = "Delete a specific category that the specific room is observing"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity deleteCategoryFromRoom(
            @PathVariable Integer categoryId,
            @PathVariable Integer roomId,
            @RequestParam String token) {
        return checkAuthAndUser(userService, token, (user) -> {
            service.deleteById(StandsRoomsCategoriesIds.builder().productCategory(categoryId).room(roomId).build());
            return ResponseEntity.ok(HttpStatus.OK);
        });
    }

    @Transactional
    @PostMapping(UtilsV1.URLS.addCategoryToRoom)
    @ApiOperation(
            value = "Add a specific category to a specific room"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity addCategoryToRoom(@RequestParam String token,
                                            @RequestBody JSONIntegerPair data) {
        return checkAuthAndUser(userService, token, (user) -> {
            Optional<StandsRooms> standsRooms = standsRoomsService.findById(data.getKey());
            Optional<ProductsTypesCategories> category = productsTypesCategoriesService.findById(data.getValue());

            if (!standsRooms.isPresent() || !category.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            ProductsTypesCategories ptc = category.get();
            StandsRooms sr = standsRooms.get();

            service.save(new StandsRoomsCategories(
                    StandsRoomsCategoriesIds.builder()
                            .productCategory(ptc.getId())
                            .room(sr.getId())
                            .build()
            ));
            return ResponseEntity.ok(HttpStatus.OK);
        });
    }

}
