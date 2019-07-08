package com.dolphinpay.server.rest_api.v1.users;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONCredentials;
import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissionsService;
import com.dolphinpay.server.rest_api.v1.platforms_standards.PlatformStandard;
import com.dolphinpay.server.rest_api.v1.platforms_standards.PlatformStandardService;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevices;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevicesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/a")
    public ResponseEntity a() {
        JSONCredentials JSONCredentials = new JSONCredentials(
                "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFmZGU4MGViMWVkZjlmM2JmNDQ4NmRkODc3YzM0YmE0NmFmYmJhMWYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI4MDYwNzEzNzAxMDItbWd2MTNzbXRtbmJkaTVsZmV2dWc3b2xuNWVqa281MTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI4MDYwNzEzNzAxMDItaHJxcmtkcWtoa2dkcmNpMDFxaTRvdmFtbDJoa2k0anYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQ3MzQ4OTc4NDg0OTQ3Mzg4MDEiLCJoZCI6InN0dWQudW5pdmUuaXQiLCJlbWFpbCI6Ijg2NDQxNEBzdHVkLnVuaXZlLml0IiwiZW1haWxfdmVyaWZpZWQiOnRydWUsIm5hbWUiOiJWTEFESVNMQVYgQk9HREFOIiwicGljdHVyZSI6Imh0dHBzOi8vbGgzLmdvb2dsZXVzZXJjb250ZW50LmNvbS8ta2J4S0FiaUZmQlUvQUFBQUFBQUFBQUkvQUFBQUFBQUFBQUEvQUNIaTNyZUR0Sy1mMEhZdm5ITlpsTm84bllaNmFyNUFvdy9zOTYtYy9waG90by5qcGciLCJnaXZlbl9uYW1lIjoiVkxBRElTTEFWIiwiZmFtaWx5X25hbWUiOiJCT0dEQU4iLCJsb2NhbGUiOiJpdCIsImlhdCI6MTU2MjU5OTc3MSwiZXhwIjoxNTYyNjAzMzcxfQ.F1HXkq22QJzAAvD3SiEv3qbtXpJH-oRf6jDHJ_aFBbjCLYBe34noloS4lwC9EVh5_6vfEUnHEKERf6_B4IClzWIZLSJZFAW5ht09heOY9sB-1q-sY4cKrgyzEFNuJkIk22q9kMNgNqolUR3Ss_ntSNLJC1l6L7Jt6efCm2FqZJyhmHQ_fucka8GkfTVzrEevo4QBsVpGexqxZI9HQ_lemwIdtePJmKapNVjr6vN3qBSL1dewjNOp-iF7ixsNejjehxCsd6i0xsDbxH_TOd4ZX3AD3K-2LQf-chocfYqdqCy5xNssRV6sgSjdGqZd1aKl2m8gJVqV4qDt6MW0LCJf0g",
                "vladislav.bogdan97@gmail.com",
                "c9EpzG4XQOA:APA91bHWkOUvqSKIAr8yrgERKYUkNZlprLq2wLMvF0dGib9x9wNSafZlL9UsvCXyVTKF2_BIm9HLYjXuTs7YEzA7KsfmJE4qx36eES3aGQ18VomZJsv8BO30UT9RCTwqVHenw1hiJmpt");
        return login(JSONCredentials);
    }

    @PostMapping(UtilsV1.URLS.authentication)
    public ResponseEntity login(@RequestBody JSONCredentials JSONCredentials) {
        return checkAuth(JSONCredentials, () -> {
            PlatformStandard platformStandard = platformStandardService.findAll().get(0);


            User user = userService.findByEmail(JSONCredentials.getEmail());
            user = user == null ? new User() : user;
            user.setUsername(JSONCredentials.getEmail());
            user.setEmail(JSONCredentials.getEmail());
            user.setStandardPlatform(platformStandard);
            user = userService.save(user);

            UsersDevices usersDevices = usersDevicesService.findByUserId(user.getId());
            usersDevices = usersDevices == null ? new UsersDevices() : usersDevices;
            usersDevices.setUser(user);
            usersDevices.setFirebaseToken(JSONCredentials.getFirebaseToken());
            usersDevicesService.save(usersDevices);
            return ResponseEntity.ok(user.getHttpResponse(platformStandard.getHttpResponse(platformsPermissionsService.findRolePermissions(
                    user.getStandardPlatform().getRole().getId()
            ))));
        });
    }

}
