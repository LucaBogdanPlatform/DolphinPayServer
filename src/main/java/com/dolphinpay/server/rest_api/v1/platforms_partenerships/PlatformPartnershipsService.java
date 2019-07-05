package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformPartnershipsService {
    @NonNull
    private PlatformPartnershipsRepository platformPartnershipsRepository;

    public List<PlatformPartnerships> findAll() {
        return platformPartnershipsRepository.findAll();
    }

    public Optional<PlatformPartnerships> findById(Integer id) {
        return platformPartnershipsRepository.findById(id);
    }

    public PlatformPartnerships save(PlatformPartnerships platformPartnerships) {
        return platformPartnershipsRepository.save(platformPartnerships);
    }

    public void deleteById(Integer id) {
        platformPartnershipsRepository.deleteById(id);
    }
}
