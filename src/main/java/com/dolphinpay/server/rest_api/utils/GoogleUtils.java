package com.dolphinpay.server.rest_api.utils;

import com.dolphinpay.server.rest_api.interfaces.ExecutableRequest;
import com.dolphinpay.server.rest_api.interfaces.UserExecutableRequest;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONAuthCredentials;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONCredentials;
import com.dolphinpay.server.rest_api.v1.users.User;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Objects;

public class GoogleUtils {
    private static final String WEB_CLIENT_ID = "806071370102-h2i7k86tb9shfgkqbibgg451svi9o3o6.apps.googleusercontent.com";
    private static final String G_ISSUER_ANDROID_OLDER = "accounts.google.com";
    private static final String G_ISSUER_ANDROID_NEW = "https://accounts.google.com";


    public static class GoogleValidationResponse {
        enum State {
            FAIL,
            SUCCESS,
            INVALID_INVALID_ID_TOKEN
        }

        @NonNull
        private final State currentState;

        private final GoogleIdToken.Payload payload;

        static GoogleValidationResponse buildInvalidIdToken() {
            return new GoogleValidationResponse(State.INVALID_INVALID_ID_TOKEN);
        }

        static GoogleValidationResponse buildGenericError() {
            return new GoogleValidationResponse(State.FAIL);
        }

        static GoogleValidationResponse buildSuccessValidation(GoogleIdToken.Payload payload) {
            return new GoogleValidationResponse(State.SUCCESS, payload);
        }

        private GoogleValidationResponse(@NonNull State state) {
            this.currentState = state;
            this.payload = null;
        }

        private GoogleValidationResponse(@NonNull State state, @NonNull GoogleIdToken.Payload payload) {
            this.currentState = state;
            this.payload = payload;
        }

        public GoogleIdToken.Payload isValid() {
            return payload;
        }

        public boolean isFailed() {
            return Objects.equals(currentState, State.FAIL);
        }

        public boolean isInvalidIdToken() {
            return Objects.equals(currentState, State.INVALID_INVALID_ID_TOKEN);
        }

    }

    @NonNull
    public static GoogleValidationResponse validate(@NonNull String idTokenString) {

        GoogleIdTokenVerifier oldVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(WEB_CLIENT_ID))
                // For Android Play Services older than 8.3 and web client
                .setIssuer(G_ISSUER_ANDROID_OLDER)
                .build();

        GoogleIdTokenVerifier newVerifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(WEB_CLIENT_ID))
                // For Android Play Services newer than 8.3
                .setIssuer(G_ISSUER_ANDROID_NEW)
                .build();

        GoogleIdToken idToken;
        try {
            idToken = oldVerifier.verify(idTokenString);
            if (idToken == null) {
                idToken = newVerifier.verify(idTokenString);
            }
            if (idToken == null) {
                return GoogleValidationResponse.buildInvalidIdToken();
            }
            return GoogleValidationResponse.buildSuccessValidation(idToken.getPayload());
        } catch (GeneralSecurityException e) {
            return GoogleValidationResponse.buildInvalidIdToken();
        } catch (IOException e) {
            return GoogleValidationResponse.buildGenericError();
        }
    }


    public static ResponseEntity checkAuth(JSONCredentials jsonCredentials, ExecutableRequest executableRequest) {
        if (jsonCredentials == null || !jsonCredentials.isValid()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        GoogleUtils.GoogleValidationResponse validation = GoogleUtils.validate(jsonCredentials.getIdToken());
        if (validation.isFailed()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (validation.isInvalidIdToken()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            return executableRequest.execute();
        }
    }

    public static ResponseEntity checkAuthAndUser(
            UserService userService,
            String token,
            UserExecutableRequest userExecutableRequest) {
        if (Objects.isNull(token)) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        GoogleUtils.GoogleValidationResponse validation = GoogleUtils.validate(token);
        if (validation.isFailed()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else if (validation.isInvalidIdToken()) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            User user = userService.findByEmail(validation.payload.getEmail());
            if (user == null) {
                return new ResponseEntity(HttpStatus.UNAUTHORIZED);
            }
            return userExecutableRequest.execute(user);
        }
    }
}
