package com.dolphinpay.server.rest_api.v1.stands;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

interface StandsRepository extends PagingAndSortingRepository<Stands, Integer> {
    Page<Stands> findAll(Pageable pageable);
}
