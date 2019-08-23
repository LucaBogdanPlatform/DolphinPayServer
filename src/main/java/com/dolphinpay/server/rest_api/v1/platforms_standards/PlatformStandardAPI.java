package com.dolphinpay.server.rest_api.v1.platforms_standards;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONGenericPlatform;
import com.dolphinpay.server.rest_api.v1.platforms_partenerships.PlatformPartnerships;
import com.dolphinpay.server.rest_api.v1.platforms_partenerships.PlatformPartnershipsService;
import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissionsService;
import com.dolphinpay.server.rest_api.v1.platforms_subscribers.PlatformSubscribers;
import com.dolphinpay.server.rest_api.v1.platforms_subscribers.PlatformSubscribersService;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class PlatformStandardAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlatformStandardAPI.class);

    @NonNull
    private UserService userService;
    @NonNull
    private PlatformSubscribersService platformSubscribersService;
    @NonNull
    private PlatformPartnershipsService platformPartnershipsService;
    @NonNull
    private PlatformsPermissionsService platformsPermissionsService;

    @GetMapping(UtilsV1.URLS.platformsAll)
    @ApiOperation(
            value = "Get all user platforms, standards, subscribers and admin",
            response = JSONGenericPlatform.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getAllPlatforms(@RequestParam String token) { // TODO paging
        return checkAuthAndUser(userService, token, (user) -> {
            ArrayList<JSONGenericPlatform> platforms = new ArrayList<>();

            PlatformSubscribers[] platformSubscribers = platformSubscribersService.findAll(user);
            PlatformPartnerships[] platformPartnerships = platformPartnershipsService.findAll(user);

            platforms.add(user.getStandardPlatform().getHttpResponse(platformsPermissionsService.findRolePermissions(
                    user.getStandardPlatform().getRole().getId()
            )));

            for (PlatformSubscribers p : platformSubscribers) {
                platforms.add(p.getHttpResponseStandard(platformsPermissionsService.findRolePermissions(
                        p.getRole().getId()
                )));
            }

            for (PlatformPartnerships p : platformPartnerships) {
                platforms.add(p.getHttpResponseStandard(platformsPermissionsService.findRolePermissions(
                        p.getRole().getId()
                )));
            }

            return ResponseEntity.ok(platforms);
        });
    }

}
