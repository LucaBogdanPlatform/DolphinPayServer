package com.dolphinpay.server.rest_api.v1.stands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandsService {
    @NonNull
    private StandsRepository standsRepository;

    public List<Stands> findAll() {
        return standsRepository.findAll();
    }

    public Optional<Stands> findById(Integer id) {
        return standsRepository.findById(id);
    }

    public Stands save(Stands user) {
        return standsRepository.save(user);
    }

    public void deleteById(Integer id) {
        standsRepository.deleteById(id);
    }

}
