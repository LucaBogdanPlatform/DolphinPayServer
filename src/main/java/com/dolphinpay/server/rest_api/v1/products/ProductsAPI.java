package com.dolphinpay.server.rest_api.v1.products;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class ProductsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductsAPI.class);

    @NonNull
    private ProductsService service;


}
