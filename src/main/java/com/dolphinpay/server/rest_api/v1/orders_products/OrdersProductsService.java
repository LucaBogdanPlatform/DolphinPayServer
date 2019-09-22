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

    public Optional<OrdersProducts> findById(OrdersProductsIds id) {
        return ordersProductsRepository.findById(id);
    }

    public OrdersProducts save(OrdersProducts user) {
        return ordersProductsRepository.save(user);
    }

    public void deleteById(OrdersProductsIds id) {
        ordersProductsRepository.deleteById(id);
    }

    public OrdersProducts[] findByRoomIdNotClosed(Integer roomId) {
        return ordersProductsRepository.findByRoomIdNotClosed(roomId);
    }

    public OrdersProducts[] findAllUserOpenOrders(Integer userId){
        return ordersProductsRepository.findAllUserOpenOrders(userId);
    }
}
