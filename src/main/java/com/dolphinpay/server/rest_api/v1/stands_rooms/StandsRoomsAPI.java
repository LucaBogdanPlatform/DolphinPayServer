package com.dolphinpay.server.rest_api.v1.stands_rooms;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONGenericPlatform;
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

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class StandsRoomsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StandsRoomsAPI.class);

    @NonNull
    private StandsRoomsService service;
}
