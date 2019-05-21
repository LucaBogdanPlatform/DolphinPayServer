package com.dolphinpay.server.rest_api.v1.users;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Integer> {
}
