package com.dolphinpay.server.rest_api.v1.products;

import com.dolphinpay.server.rest_api.v1.products_brands.ProductsBrands;
import com.dolphinpay.server.rest_api.v1.products_types.ProductsTypes;
import com.dolphinpay.server.rest_api.v1.stands.Stands;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}
