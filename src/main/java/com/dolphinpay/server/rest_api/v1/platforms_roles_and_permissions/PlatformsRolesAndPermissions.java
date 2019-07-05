package com.dolphinpay.server.rest_api.v1.platforms_roles_and_permissions;

import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissions;
import com.dolphinpay.server.rest_api.v1.platforms_roles.PlatformsRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "platforms_permissions_and_roles")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(PlatformsRolesAndPermissionsIds.class)
public class PlatformsRolesAndPermissions {
    @Id
    @ManyToOne
    @JoinColumn(name = "z_role")
    private PlatformsRoles role;
    @Id
    @ManyToOne
    @JoinColumn(name = "z_permission")
    private PlatformsPermissions permission;
}
