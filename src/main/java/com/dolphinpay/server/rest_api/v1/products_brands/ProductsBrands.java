package com.dolphinpay.server.rest_api.v1.products_brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products_brands")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductsBrands {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "z_id", insertable = false)
    private int id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}