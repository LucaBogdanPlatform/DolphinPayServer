package com.dolphinpay.server.rest_api.v1.stands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandsService {
    @NonNull
    private StandsRepository standsRepository;

    public Page<Stands> findAll(Pageable pageable) {
        return standsRepository.findAll(pageable);
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

    public long count() {
        return standsRepository.count();
    }

}
