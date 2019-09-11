package com.dolphinpay.server.rest_api.v1.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface ProductsRepository extends JpaRepository<Products, Integer> {

    Page<Products> findAll(Pageable pageable);


    @Query(
            value = "SELECT p.* " +
                    "FROM products p " +
                    "INNER JOIN products_types pt ON p.z_type = pt.z_id " +
                    "WHERE p.z_stand = :standId AND pt.z_category = :categoryId",
            nativeQuery = true
    )
    Products[] getAllProductsOfCategoryOfStand(Integer standId, Integer categoryId);
}
