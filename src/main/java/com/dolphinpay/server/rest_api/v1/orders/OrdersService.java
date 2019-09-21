package com.dolphinpay.server.rest_api.v1.orders;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    @NonNull
    private OrdersRepository ordersRepository;

    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    public Optional<Orders> findById(Integer id) {
        return ordersRepository.findById(id);
    }

    public Orders save(Orders user) {
        return ordersRepository.save(user);
    }

    public void deleteById(Integer id) {
        ordersRepository.deleteById(id);
    }

    public int countStandOpenOrders(Integer standId){
        return ordersRepository.countStandOpenOrders(standId);

    }
}
