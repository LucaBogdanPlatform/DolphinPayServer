package com.dolphinpay.server.rest_api.v1.products_types_categories;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class ProductsTypesCategoriesAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductsTypesCategoriesAPI.class);

    @NonNull
    private ProductsTypesCategoriesService service;

    @NonNull
    private UserService userService;


    @GetMapping(UtilsV1.URLS.categoriesOfRoom)
    @ApiOperation(
            value = "Get all categories observing by the required room",
            response = ProductsTypesCategories.JSONProductsTypesCategories.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getAllProductsCategoriesOfRoom(
            @RequestParam String token,
            @PathVariable("roomId") Integer roomId) {
        return checkAuthAndUser(userService, token, (user) -> {
            ArrayList<ProductsTypesCategories.JSONProductsTypesCategories> productsTypesCategories = new ArrayList<>();


            ProductsTypesCategories[] result = service.getAllCategoriesOfRoom(roomId);
            for (ProductsTypesCategories p : result) {
                productsTypesCategories.add(p.getResponse());
            }

            return ResponseEntity.ok(productsTypesCategories);
        });
    }
}
