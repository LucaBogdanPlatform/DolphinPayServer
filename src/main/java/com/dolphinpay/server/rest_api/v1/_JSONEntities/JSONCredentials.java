package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class JSONCredentials {
    @NonNull
    private String idToken;
    @NonNull
    private String email;
    @NonNull
    private String firebaseToken;


    public boolean isValid() {
        return this.getIdToken() != null &&
                this.getEmail() != null &&
                this.getFirebaseToken() != null;
    }
}
