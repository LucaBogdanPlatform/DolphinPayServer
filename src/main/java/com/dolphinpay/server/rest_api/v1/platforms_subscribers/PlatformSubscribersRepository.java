package com.dolphinpay.server.rest_api.v1.platforms_subscribers;

import com.dolphinpay.server.rest_api.v1.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PlatformSubscribersRepository extends JpaRepository<PlatformSubscribers, Integer> {

    PlatformSubscribers[] findByUser(User user);

    Optional<PlatformSubscribers> findByUserAndId(User user, Integer id);
}
