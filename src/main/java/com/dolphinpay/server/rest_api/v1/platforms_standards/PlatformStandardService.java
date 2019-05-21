package com.dolphinpay.server.rest_api.v1.platforms_standards;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformStandardService {
    @NonNull
    private PlatformStandardRepository platformStandardRepository;

    public List<PlatformStandard> findAll() {
        return platformStandardRepository.findAll();
    }

    public Optional<PlatformStandard> findById(Integer id) {
        return platformStandardRepository.findById(id);
    }

    public PlatformStandard save(PlatformStandard platformStandard) {
        return platformStandardRepository.save(platformStandard);
    }

    public void deleteById(Integer id) {
        platformStandardRepository.deleteById(id);
    }
}
