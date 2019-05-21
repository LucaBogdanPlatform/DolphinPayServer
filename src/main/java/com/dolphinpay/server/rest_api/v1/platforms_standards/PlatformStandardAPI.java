package com.dolphinpay.server.rest_api.v1.platforms_standards;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1.users.User;
import com.dolphinpay.server.rest_api.v1.users.UserAPI;
import com.dolphinpay.server.rest_api.v1.users.UserService;
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
public class PlatformStandardAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlatformStandardAPI.class);

    @NonNull
    private PlatformStandardService service;

    @GetMapping("/platformsStrands")
    public ResponseEntity<List<PlatformStandard>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
