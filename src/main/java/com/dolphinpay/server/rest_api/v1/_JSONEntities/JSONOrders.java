package com.dolphinpay.server.rest_api.v1._JSONEntities;

import com.dolphinpay.server.rest_api.v1.orders_products.OrdersProducts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class JSONOrders {
    ArrayList<OrdersProducts.JSONOrderProduct> ordersProducts = new ArrayList<>();
}
