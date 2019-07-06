package com.dolphinpay.server.rest_api.v1.products;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductsRepository extends JpaRepository<Products, Integer> {
}
