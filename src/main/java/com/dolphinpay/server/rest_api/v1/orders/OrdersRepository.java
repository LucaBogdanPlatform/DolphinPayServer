package com.dolphinpay.server.rest_api.v1.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(
            value = "SELECT count(*) " +
                    "FROM orders o " +
                    "INNER JOIN orders_products op ON o.z_id = op.z_order " +
                    "INNER JOIN products p ON p.z_id = op.z_product " +
                    "WHERE p.z_stand = standId AND o.z_state = 2 ",
            nativeQuery = true
    )
    int countStandOpenOrders(Integer standId);
}
