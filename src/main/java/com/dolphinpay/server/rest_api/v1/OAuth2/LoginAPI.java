package com.dolphinpay.server.rest_api.v1.OAuth2;


import com.dolphinpay.server.rest_api.utils.GoogleUtils;
import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class LoginAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(LoginAPI.class);

    @NonNull
    private UserService service;


    @PostMapping("/auth")
    public ResponseEntity login(@RequestBody Credentials credentials) {
        GoogleUtils.GoogleValidationResponse validation = GoogleUtils.validate(credentials.getIdToken());

        if (validation.isFailed()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (validation.isInvalidIdToken()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            return ResponseEntity.ok(credentials);
        }
    }

}
