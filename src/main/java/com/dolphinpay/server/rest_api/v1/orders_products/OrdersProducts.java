package com.dolphinpay.server.rest_api.v1.orders_products;

import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStates;
import com.dolphinpay.server.rest_api.v1.products.Products;
import com.dolphinpay.server.rest_api.v1.products_brands.ProductsBrands;
import com.dolphinpay.server.rest_api.v1.products_types.ProductsTypes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders_products")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdersProducts {
    public enum StatesIds {
        STATE_NEW(1),
        STATE_PREPARE(2),
        STATE_READY(3),
        STATE_CLOSED(4);

        private final int state;

        StatesIds(int i) {
            this.state = i;
        }
    }

    @EmbeddedId
    private OrdersProductsIds ids;

    @ManyToOne
    @JoinColumn(name = "z_state", insertable = false)
    private OrdersStates state;

    @Column(name = "z_quantity")
    private int quantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_expected_start_prepare")
    private Date expectedStartTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_expected_end_prepare")
    private Date expectedEndTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_closure_time", insertable = false)
    private Date officialClosureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_sum_optional_time", insertable = false)
    private Date sumOptionalTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;


    public Map<String, String> toMap(Products.JSONProducts products) throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        String order = mapper.writeValueAsString(getResponse(products));
        map.put("order", order);
        return map;
    }

    private JSONOrder getResponse(Products.JSONProducts products){
        return JSONOrder.builder()
                .id(this.ids.getOrder())
                .quantity(this.quantity)
                .products(products)
                .expectedStartTime(this.expectedStartTime)
                .expectedEndTime(this.expectedEndTime)
                .officialClosureTime(this.officialClosureTime)
                .sumOptionalTime(this.sumOptionalTime)
                .build();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JSONOrder {
        private int id;
        private Products.JSONProducts products;
        private int quantity;
        private Date expectedStartTime;
        private Date expectedEndTime;
        private Date officialClosureTime;
        private Date sumOptionalTime;
    }}
