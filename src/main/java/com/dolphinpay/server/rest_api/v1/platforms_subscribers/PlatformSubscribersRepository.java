package com.dolphinpay.server.rest_api.v1.platforms_subscribers;

import com.dolphinpay.server.rest_api.v1.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

interface PlatformSubscribersRepository extends JpaRepository<PlatformSubscribers, Integer> {

    PlatformSubscribers[] findByUser(User user);
}
