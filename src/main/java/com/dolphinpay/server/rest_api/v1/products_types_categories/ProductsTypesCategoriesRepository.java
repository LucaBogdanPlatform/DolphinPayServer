package com.dolphinpay.server.rest_api.v1.products_types_categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface ProductsTypesCategoriesRepository extends JpaRepository<ProductsTypesCategories, Integer> {

    @Query(
            value = "SELECT * " +
                    "FROM products_types_categories ptc " +
                    "WHERE ptc.z_id IN (" +
                    "SELECT z_product_type_category " +
                    "FROM stands_rooms_categories stc " +
                    "WHERE stc.z_room = :roomId)",
            nativeQuery = true
    )
    ProductsTypesCategories[] getCategoriesOfRoom(Integer roomId);

    @Query(
            value = "SELECT * " +
                    "FROM products_types_categories ptc " +
                    "WHERE ptc.z_id IN ( " +
                    "SELECT pt.z_category " +
                    "FROM products_types pt " +
                    "INNER JOIN products p ON pt.z_id = p.z_type " +
                    "WHERE p.z_stand = :standId) ",
            nativeQuery = true
    )
    ProductsTypesCategories[] getCategoriesOfStand(Integer standId);
}
