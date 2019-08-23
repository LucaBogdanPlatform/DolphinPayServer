package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import com.dolphinpay.server.rest_api.v1.users.User;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformPartnershipsService {
    @NonNull
    private PlatformPartnershipsRepository platformPartnershipsRepository;

    public Optional<PlatformPartnerships> findById(Integer id) {
        return platformPartnershipsRepository.findById(id);
    }

    public PlatformPartnerships save(PlatformPartnerships platformPartnerships) {
        return platformPartnershipsRepository.save(platformPartnerships);
    }


    public void deleteById(Integer id) {
        platformPartnershipsRepository.deleteById(id);
    }

    public Page<PlatformPartnerships> findAll(Pageable pageable) {
        return platformPartnershipsRepository.findAll(pageable);
    }

    public PlatformPartnerships[] findAll(User user){
        return platformPartnershipsRepository.findByUser(user);
    }

    public long count() {
        return platformPartnershipsRepository.count();
    }
}
