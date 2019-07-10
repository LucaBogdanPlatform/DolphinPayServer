package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JSONAuthCredentials {
    @NonNull
    private String idToken;
    @NonNull
    private String email;

    public boolean isValid() {
        return this.getIdToken() != null &&
                this.getEmail() != null;
    }
}
