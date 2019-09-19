package com.dolphinpay.server.rest_api.v1.users_devices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface UsersDevicesRepository extends JpaRepository<UsersDevices, Integer> {
    @Query(
            value = "SELECT * FROM users_devices ud WHERE ud.z_user = ?1",
            nativeQuery = true)
    UsersDevices findByUserId(int userId);

    @Query(
            value = "SELECT ud.* FROM users_devices ud " +
                    "INNER JOIN users u ON u.z_id = ud.z_user " +
                    "INNER JOIN platforms_subscribers ps ON ps.z_user = u.z_id " +
                    "INNER JOIN stands_rooms sr ON sr.z_id = ps.z_stand_room " +
                    "INNER JOIN stands_rooms_categories src ON src.z_room = sr.z_id " +
                    "INNER JOIN products_types_categories ptc ON ptc.z_id = src.z_product_type_category " +
                    "WHERE sr.z_stand = :standId AND ptc.z_id = :categoryId",
            nativeQuery = true)
    UsersDevices[] findAllObservingWithPartnershipObservingCategory(Integer standId, Integer categoryId);
}
