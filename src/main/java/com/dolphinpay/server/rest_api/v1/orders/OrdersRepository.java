package com.dolphinpay.server.rest_api.v1.orders;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrdersRepository extends JpaRepository<Orders, Integer> {


}
