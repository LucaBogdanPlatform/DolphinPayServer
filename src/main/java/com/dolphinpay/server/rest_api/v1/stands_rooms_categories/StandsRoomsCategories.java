package com.dolphinpay.server.rest_api.v1.stands_rooms_categories;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "stands_rooms_categories")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StandsRoomsCategories {
    @EmbeddedId
    private StandsRoomsCategoriesIds ids;
}
