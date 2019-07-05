package com.dolphinpay.server.rest_api.v1.platforms_permissions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformsPermissionsService {
    @NonNull
    private PlatformsPermissionsRepository platformsPermissionsRepository;

    public List<PlatformsPermissions> findAll() {
        return platformsPermissionsRepository.findAll();
    }

    public Optional<PlatformsPermissions> findById(Integer id) {
        return platformsPermissionsRepository.findById(id);
    }

    public PlatformsPermissions save(PlatformsPermissions user) {
        return platformsPermissionsRepository.save(user);
    }

    public void deleteById(Integer id) {
        platformsPermissionsRepository.deleteById(id);
    }

}
