package com.dolphinpay.server.rest_api.v1.orders_products;

import com.dolphinpay.server.rest_api.v1.orders.Orders;
import com.dolphinpay.server.rest_api.v1.products.Products;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrdersProductsIds implements Serializable {
    private Orders order;
    private Products product;

}
