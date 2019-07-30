package com.dolphinpay.server.rest_api.v1.products;

import com.dolphinpay.server.rest_api.v1.stands.Stands;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductsRepository extends JpaRepository<Products, Integer> {

    Page<Products> findAll(Pageable pageable);
}
