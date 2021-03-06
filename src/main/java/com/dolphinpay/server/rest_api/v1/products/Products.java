package com.dolphinpay.server.rest_api.v1.products;

import com.dolphinpay.server.rest_api.v1.products_brands.ProductsBrands;
import com.dolphinpay.server.rest_api.v1.products_types.ProductsTypes;
import com.dolphinpay.server.rest_api.v1.stands.Stands;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products", uniqueConstraints = {@UniqueConstraint(columnNames = {"z_stand", "z_type", "z_brand"})})
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "z_id")
    private int id;

    @OneToOne
    @JoinColumn(name = "z_stand")
    private Stands stand;

    @ManyToOne
    @JoinColumn(name = "z_type")
    private ProductsTypes type;

    @ManyToOne
    @JoinColumn(name = "z_brand")
    private ProductsBrands brand;

    @Column(name = "z_name")
    private String name;

    @Column(name = "z_price_euro")
    private Double price;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;

    public JSONProducts getResponse() {
        return JSONProducts
                .builder()
                .id(this.id)
                .type(this.type.getResponse())
                .brand(this.brand.getResponse())
                .name(this.name)
                .price(this.price)
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JSONProducts {
        private int id;
        private ProductsTypes.JSONProductsTypes type;
        private ProductsBrands.JSONProductsBrands brand;
        private Double price;
        private String name;
    }

    @Data
    @Builder
    public static class Filter {
        private Integer stand;
        private String name;
        private Integer category;
        private Integer type;
    }
}
