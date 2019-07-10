package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.*;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JSONCredentials extends JSONAuthCredentials {
    @NonNull
    private String firebaseToken;

    public boolean isValid() {
        return this.getIdToken() != null &&
                this.getEmail() != null &&
                this.getFirebaseToken() != null;
    }
}
