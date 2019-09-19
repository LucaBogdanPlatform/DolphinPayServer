package com.dolphinpay.server.rest_api.v1.orders_products;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
@Builder
public class OrdersProductsIds implements Serializable {
    @Column(name = "z_order")
    private Integer order;

    @Column(name = "z_product")
    private Integer product;

}
