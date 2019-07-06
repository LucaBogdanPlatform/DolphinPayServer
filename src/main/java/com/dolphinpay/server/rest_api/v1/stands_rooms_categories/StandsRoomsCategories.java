package com.dolphinpay.server.rest_api.v1.stands_rooms_categories;

import com.dolphinpay.server.rest_api.v1.products_types.ProductsTypes;
import com.dolphinpay.server.rest_api.v1.products_types_categories.ProductsTypesCategories;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "stands_rooms_categories")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(StandsRoomsCategoriesIds.class)
public class StandsRoomsCategories {
    @Id
    @ManyToOne
    @JoinColumn(name = "z_room")
    private StandsRooms room;

    @Id
    @ManyToOne
    @JoinColumn(name = "z_product_type_category")
    private ProductsTypesCategories productCategory;

}
