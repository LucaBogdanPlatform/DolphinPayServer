package com.dolphinpay.server.rest_api.v1.OAuth2;


import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1.platforms_standards.PlatformStandardService;
import com.dolphinpay.server.rest_api.v1.users.User;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevices;
import com.dolphinpay.server.rest_api.v1.users_devices.UsersDevicesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuth;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class LoginAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAPI.class);

    @NonNull
    private UserService userService;

    @NonNull
    private UsersDevicesService usersDevicesService;
//    @GetMapping("/a")
//    public ResponseEntity a() {
//        Credentials credentials = new Credentials(
//                "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFmZGU4MGViMWVkZjlmM2JmNDQ4NmRkODc3YzM0YmE0NmFmYmJhMWYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI4MDYwNzEzNzAxMDItbWd2MTNzbXRtbmJkaTVsZmV2dWc3b2xuNWVqa281MTguYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiI4MDYwNzEzNzAxMDItaHJxcmtkcWtoa2dkcmNpMDFxaTRvdmFtbDJoa2k0anYuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQyNjAzNzA5NTY5NTMxMzIwMTEiLCJlbWFpbCI6InZsYWRpc2xhdi5ib2dkYW45N0BnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IlZsYWRpc2xhdiBCb2dkYW4iLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDQuZ29vZ2xldXNlcmNvbnRlbnQuY29tLy1oVGlkb1gyQkdVWS9BQUFBQUFBQUFBSS9BQUFBQUFBQUFBQS9BQ0hpM3JjWUpPWndjY2FJNlFVTU9TeTdGNUQ3bWlDTUtBL3M5Ni1jL3Bob3RvLmpwZyIsImdpdmVuX25hbWUiOiJWbGFkaXNsYXYiLCJmYW1pbHlfbmFtZSI6IkJvZ2RhbiIsImxvY2FsZSI6Iml0IiwiaWF0IjoxNTYyMzMzOTA1LCJleHAiOjE1NjIzMzc1MDV9.UbzOqnu-1dBdKWzvoBJBqDPWLb-cdB-QVon57y_qt_hdNg-3yyQ356PvOQIaMRIJ92vxFrFnkZNKisFaiprwLpsRszNW5Pjoggxd45sG4D7d5Pn52wQqEwpm3qsip89zilKbzWFVJmCYMNL5wzUpptDpiAcYgXhaEHPsuzzcjVtievUtcFq8L5hjjfdHXQP_xoguGDCJKetTODmWO3U7cAv4B0VwL2Cqn7BYYl-8zHE37slyHfCyuaWJ9K9hWQmFNLh0FeOl1s9Fz9cxRfFk9StH_B5d2OyUue21uQsmemGk98s0CaDVl9Ni5LPCs75qA6RSZIQqkQpQnFKIi8sKjw",
//                "vladislav.bogdan97@gmail.com",
//                "c9EpzG4XQOA:APA91bHWkOUvqSKIAr8yrgERKYUkNZlprLq2wLMvF0dGib9x9wNSafZlL9UsvCXyVTKF2_BIm9HLYjXuTs7YEzA7KsfmJE4qx36eES3aGQ18VomZJsv8BO30UT9RCTwqVHenw1hiJmpt");
//        return login(credentials);
//    }

    @PostMapping("/auth")
    public ResponseEntity login(@RequestBody Credentials credentials) {
        return checkAuth(credentials, () -> {
            User user = userService.findByEmail(credentials.getEmail());
            user = user == null ? new User() : user;
            user.setUsername(credentials.getEmail());
            user.setEmail(credentials.getEmail());
            user = userService.save(user);

            UsersDevices usersDevices = usersDevicesService.findByUserId(user.getId());
            usersDevices = usersDevices == null ? new UsersDevices() : usersDevices;
            usersDevices.setUser(user);
            usersDevices.setFirebaseToken(credentials.getFirebaseToken());
            usersDevicesService.save(usersDevices);
            return ResponseEntity.ok(credentials);
        });
    }

}
