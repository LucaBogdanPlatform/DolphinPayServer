package com.dolphinpay.server.rest_api.v1.orders_products;

import com.dolphinpay.server.rest_api.v1.orders.Orders;
import com.dolphinpay.server.rest_api.v1.orders_states.OrdersStates;
import com.dolphinpay.server.rest_api.v1.products.Products;
import com.dolphinpay.server.rest_api.v1.products_types_categories.ProductsTypesCategories;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRooms;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders_products")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@IdClass(OrdersProductsIds.class)
public class OrdersProducts {
    @Id
    @ManyToOne
    @JoinColumn(name = "z_order")
    private Orders order;

    @Id
    @ManyToOne
    @JoinColumn(name = "z_product")
    private Products product;

    @ManyToOne
    @JoinColumn(name = "z_state")
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
    @Column(name = "z_closure_time")
    private Date officialClosureTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_sum_optional_time")
    private Date sumOptionalTime;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_creation_time")
    private Date creationTime;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "z_last_update_time")
    private Date lastUpdateTime;
}
