package com.dolphinpay.server.rest_api.v1.stands_rooms_categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class StandsRoomsCategoriesIds implements Serializable {
    @Column(name = "z_room")
    private Integer room;
    @Column(name = "z_product_type_category")
    private Integer productCategory;
}
