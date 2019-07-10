package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


interface PlatformPartnershipsRepository extends PagingAndSortingRepository<PlatformPartnerships, Integer> {
    Page<PlatformPartnerships> findAll(Pageable pageable);
}
