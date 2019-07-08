package com.dolphinpay.server.rest_api.v1.platforms_permissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface PlatformsPermissionsRepository extends JpaRepository<PlatformsPermissions, Integer> {


    @Query(
            value = "SELECT * " +
                    "FROM platforms_permissions pp " +
                    "WHERE pp.z_id IN(" +
                        "SELECT z_permission " +
                        "FROM platforms_permissions_and_roles pr " +
                        "WHERE pr.z_role = ?1 " +
                    ")",
            nativeQuery = true)
    List<PlatformsPermissions> findRolePermissions(int roleId);

}
