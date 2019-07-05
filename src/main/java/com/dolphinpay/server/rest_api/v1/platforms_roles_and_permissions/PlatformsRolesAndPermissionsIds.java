package com.dolphinpay.server.rest_api.v1.platforms_roles_and_permissions;

import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissions;
import com.dolphinpay.server.rest_api.v1.platforms_roles.PlatformsRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatformsRolesAndPermissionsIds implements Serializable {
    private PlatformsRoles role;
    private PlatformsPermissions permission;
}
