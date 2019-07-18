package com.dolphinpay.server.rest_api.v1.products_types;

import com.dolphinpay.server.rest_api.v1.products_types_categories.ProductsTypesCategories;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products_types")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "z_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "z_category")
    private ProductsTypesCategories category;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;

    public JSONProductsTypes getResponse() {
        return JSONProductsTypes.builder().id(this.id).category(this.category.getResponse()).build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JSONProductsTypes {
        private int id;
        private ProductsTypesCategories.JSONProductsTypesCategories category;
    }
}
