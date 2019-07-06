package com.dolphinpay.server.rest_api.v1.orders_products;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersProductsService {
    @NonNull
    private OrdersProductsRepository ordersProductsRepository;

    public List<OrdersProducts> findAll() {
        return ordersProductsRepository.findAll();
    }

    public Optional<OrdersProducts> findById(Integer id) {
        return ordersProductsRepository.findById(id);
    }

    public OrdersProducts save(OrdersProducts user) {
        return ordersProductsRepository.save(user);
    }

    public void deleteById(Integer id) {
        ordersProductsRepository.deleteById(id);
    }

}
