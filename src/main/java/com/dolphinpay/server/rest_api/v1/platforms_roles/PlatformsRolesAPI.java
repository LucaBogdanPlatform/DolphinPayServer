package com.dolphinpay.server.rest_api.v1.platforms_roles;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class PlatformsRolesAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlatformsRolesAPI.class);

    @NonNull
    private PlatformsRolesService service;

}
