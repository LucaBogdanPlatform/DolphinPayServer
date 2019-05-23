package com.dolphinpay.server.rest_api.v1.OAuth2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;


@AllArgsConstructor
@Data
class Credentials {
    @NonNull
    private String idToken;
    private String username;
}
