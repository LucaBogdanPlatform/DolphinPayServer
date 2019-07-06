package com.dolphinpay.server.rest_api.v1.orders_states;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersStatesService {
    @NonNull
    private OrdersStatesRepository ordersStatesRepository;

    public List<OrdersStates> findAll() {
        return ordersStatesRepository.findAll();
    }

    public Optional<OrdersStates> findById(Integer id) {
        return ordersStatesRepository.findById(id);
    }

    public OrdersStates save(OrdersStates user) {
        return ordersStatesRepository.save(user);
    }

    public void deleteById(Integer id) {
        ordersStatesRepository.deleteById(id);
    }

}
