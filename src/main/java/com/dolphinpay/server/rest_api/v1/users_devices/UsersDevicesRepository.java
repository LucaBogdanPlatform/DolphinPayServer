package com.dolphinpay.server.rest_api.v1.users_devices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface UsersDevicesRepository extends JpaRepository<UsersDevices, Integer> {
    @Query(
            value = "SELECT * FROM users_devices ud WHERE ud.z_user = ?1",
            nativeQuery = true)
    UsersDevices findByUserId(int userId);
}
