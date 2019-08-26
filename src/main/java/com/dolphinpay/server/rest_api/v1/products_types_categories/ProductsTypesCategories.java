package com.dolphinpay.server.rest_api.v1.products_types_categories;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products_types_categories")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsTypesCategories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

    @Column(name = "z_name")
    private String name;

    @Column(name = "z_position_mapping")
    private int positionMapping;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;

    public JSONProductsTypesCategories getResponse() {
        return JSONProductsTypesCategories.builder()
                .id(this.id)
                .name(this.name)
                .positionMapping(this.positionMapping)
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JSONProductsTypesCategories {
        private int id;
        private String name;
        private int positionMapping;
    }
}
