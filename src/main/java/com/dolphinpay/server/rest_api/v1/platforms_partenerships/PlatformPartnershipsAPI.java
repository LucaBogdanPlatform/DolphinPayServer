package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1.platforms_subscribers.PlatformSubscribers;
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
public class PlatformPartnershipsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlatformPartnershipsAPI.class);

    @NonNull
    private PlatformPartnershipsService service;

}
