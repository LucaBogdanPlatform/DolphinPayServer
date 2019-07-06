package com.dolphinpay.server.rest_api.v1.stands_rooms_categories;

import com.dolphinpay.server.rest_api.v1.products_types_categories.ProductsTypesCategories;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandsRoomsCategoriesIds implements Serializable {
    private StandsRooms room;
    private ProductsTypesCategories productCategory;
}
