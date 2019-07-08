package com.dolphinpay.server.rest_api.v1._JSONEntities;

import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONGenericPlatform {
    private int id;
    private String name;
    private JSONRole role;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JSONRole {
        private String name;
        private JSONPermission[] permissions;

        public void setPermissions(List<PlatformsPermissions> jsonRolePermissions) {
            JSONPermission[] jsonPermissions = new JSONPermission[jsonRolePermissions.size()];
            for (int i = 0; i < jsonRolePermissions.size(); i++) {
                jsonPermissions[i] = jsonRolePermissions.get(i).getHttpResponse();
            }
            this.permissions = jsonPermissions;
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JSONPermission {
        private String name;
    }
}
