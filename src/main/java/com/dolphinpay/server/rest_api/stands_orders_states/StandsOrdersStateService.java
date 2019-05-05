package com.dolphinpay.server.rest_api.stands_orders_states;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandsOrdersStateService {

    @NonNull
    private StandsOrdersStateRepository standsOrdersStateRepository;

    public List<StandsOrdersState> findAll() {
        return standsOrdersStateRepository.findAll();
    }

    public Optional<StandsOrdersState> findById(Integer id) {
        return standsOrdersStateRepository.findById(id);
    }

    public StandsOrdersState save(StandsOrdersState stock) {
        return standsOrdersStateRepository.save(stock);
    }

    public void deleteById(Integer id) {
        standsOrdersStateRepository.deleteById(id);
    }

}
