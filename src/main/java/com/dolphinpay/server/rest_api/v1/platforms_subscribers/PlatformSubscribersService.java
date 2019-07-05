package com.dolphinpay.server.rest_api.v1.platforms_subscribers;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformSubscribersService {
    @NonNull
    private PlatformSubscribersRepository platformSubscribersRepository;

    public List<PlatformSubscribers> findAll() {
        return platformSubscribersRepository.findAll();
    }

    public Optional<PlatformSubscribers> findById(Integer id) {
        return platformSubscribersRepository.findById(id);
    }

    public PlatformSubscribers save(PlatformSubscribers platformSubscribers) {
        return platformSubscribersRepository.save(platformSubscribers);
    }

    public void deleteById(Integer id) {
        platformSubscribersRepository.deleteById(id);
    }
}