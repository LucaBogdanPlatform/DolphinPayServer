package com.dolphinpay.server.rest_api.v1.orders_products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface OrdersProductsRepository extends JpaRepository<OrdersProducts, Integer> {

    @Query(
            value = "SELECT op.* " +
                    "FROM orders_products op " +
                        "INNER JOIN products p ON op.z_product = p.z_id " +
                        "INNER JOIN products_types pt ON p.z_id = pt.z_id " +
                        "INNER JOIN products_types_categories ptc ON pt.z_id = ptc.z_id " +
                        "INNER JOIN stands_rooms_categories src ON ptc.z_id = src.z_product_type_category " +
                        "INNER JOIN stands_rooms sr ON src.z_room = sr.z_id " +
                    "WHERE sr.z_id = :roomId AND op.z_closure_time IS NULL " +
                    "ORDER BY op.z_expected_end_prepare ",
            nativeQuery = true
    )
    OrdersProducts[] findByRoomIdNotClosed(Integer roomId);
}
