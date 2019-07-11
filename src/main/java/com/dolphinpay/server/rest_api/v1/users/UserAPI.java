package com.dolphinpay.server.rest_api.v1.users;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONCredentials;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONGenericPlatform;
import com.dolphinpay.server.rest_api.v1._JSONEntities.swagger_responses.JSONPagingListPlatformsPartnerships;
import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissionsService;
import com.dolphinpay.server.rest_api.v1.platforms_standards.PlatformStandard;
import com.dolphinpay.server.rest_api.v1.platforms_standards.PlatformStandardService;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevices;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevicesService;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuth;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class UserAPI {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserAPI.class);

    @NonNull
    private UserService userService;
    @NonNull
    private UsersDevicesService usersDevicesService;
    @NonNull
    private PlatformStandardService platformStandardService;
    @NonNull
    private PlatformsPermissionsService platformsPermissionsService;

    @PostMapping(UtilsV1.URLS.authentication)
    @ApiOperation(
            value = "Perform server auth",
            response = JSONGenericPlatform.class
    )
    public ResponseEntity login(@RequestBody JSONCredentials jsonCredentials) {
        return checkAuth(jsonCredentials, () -> {
            PlatformStandard platformStandard = platformStandardService.findAll().get(0);

            User user = userService.findByEmail(jsonCredentials.getEmail());
            user = user == null ? new User() : user;
            user.setUsername(jsonCredentials.getEmail());
            user.setEmail(jsonCredentials.getEmail());
            user.setStandardPlatform(platformStandard);
            user = userService.save(user);

            UsersDevices usersDevices = usersDevicesService.findByUserId(user.getId());
            usersDevices = usersDevices == null ? new UsersDevices() : usersDevices;
            usersDevices.setUser(user);
            usersDevices.setFirebaseToken(jsonCredentials.getFirebaseToken());
            usersDevicesService.save(usersDevices);
            return ResponseEntity.ok(user.getHttpResponse(platformStandard.getHttpResponse(platformsPermissionsService.findRolePermissions(
                    user.getStandardPlatform().getRole().getId()
            ))));
        });
    }

}
