package com.dolphinpay.server.rest_api.v1.products_types_categories;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class ProductsTypesCategoriesAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductsTypesCategoriesAPI.class);

    @NonNull
    private ProductsTypesCategoriesService service;

    @GetMapping("/products/brands")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(service.findAll());
    }
}
