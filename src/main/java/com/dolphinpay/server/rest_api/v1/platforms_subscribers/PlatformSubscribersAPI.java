package com.dolphinpay.server.rest_api.v1.platforms_subscribers;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1.platforms_standards.PlatformStandard;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class PlatformSubscribersAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlatformSubscribersAPI.class);

    @NonNull
    private PlatformSubscribersService service;


}