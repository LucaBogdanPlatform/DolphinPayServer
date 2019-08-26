package com.dolphinpay.server.rest_api.v1.stands_rooms;

import com.dolphinpay.server.rest_api.v1.products_types_categories.ProductsTypesCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

interface StandsRoomsRepository extends JpaRepository<StandsRooms, Integer> {
}
