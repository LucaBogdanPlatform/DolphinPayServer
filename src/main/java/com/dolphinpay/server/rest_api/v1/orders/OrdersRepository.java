package com.dolphinpay.server.rest_api.v1.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface OrdersRepository extends JpaRepository<Orders, Integer> {

    @Query(
            value = "SELECT count(*) " +
                    "FROM orders o " +
                    "INNER JOIN orders_products op ON o.z_id = op.z_order " +
                    "INNER JOIN products p ON p.z_id = op.z_product " +
                    "WHERE p.z_stand = :standId AND o.z_state = 2 ",
            nativeQuery = true
    )
    int countStandOpenOrders(Integer standId);

    @Query(
            value = "SELECT count(*) " +
                    "FROM orders_products op " +
                    "WHERE op.z_order = :orderId AND z_closure_time IS NULL",
            nativeQuery = true
    )
    int countProductsToPrepareOfOrder(Integer orderId);

    @Query(
            value = "SELECT o.* " +
                    "FROM orders o " +
                    "WHERE o.z_valid_retire_code = :retireOrderCode",
            nativeQuery = true
    )
    Optional<Orders> findByRetireCode(String retireOrderCode);
}
