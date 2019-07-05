package com.dolphinpay.server.rest_api.v1.OAuth2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;


@AllArgsConstructor
@Data
public class Credentials {
    @NonNull
    private String idToken;
    @NonNull
    private String email;
    @NonNull
    private String firebaseToken;
}
