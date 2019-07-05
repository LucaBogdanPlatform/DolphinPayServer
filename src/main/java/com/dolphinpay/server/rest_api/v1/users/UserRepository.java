package com.dolphinpay.server.rest_api.v1.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            value = "SELECT * FROM users u WHERE u.z_email = ?1",
            nativeQuery = true)
    User findByEmail(String email);
}
