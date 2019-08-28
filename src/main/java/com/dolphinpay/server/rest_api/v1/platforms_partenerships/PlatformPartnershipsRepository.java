package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import com.dolphinpay.server.rest_api.v1.stands.Stands;
import com.dolphinpay.server.rest_api.v1.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


interface PlatformPartnershipsRepository extends PagingAndSortingRepository<PlatformPartnerships, Integer> {
    Page<PlatformPartnerships> findAll(Pageable pageable);

    PlatformPartnerships[] findByUser(User user);

    Optional<PlatformPartnerships> findByStandAndUser(Stands stand, User user);
}
