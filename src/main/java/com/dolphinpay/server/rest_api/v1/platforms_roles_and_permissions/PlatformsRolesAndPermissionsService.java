package com.dolphinpay.server.rest_api.v1.platforms_roles_and_permissions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformsRolesAndPermissionsService {
    @NonNull
    private PlatformsRolesAndPermissionsRepository platformsRolesAndPermissionsRepository;

    public List<PlatformsRolesAndPermissions> findAll() {
        return platformsRolesAndPermissionsRepository.findAll();
    }

    public Optional<PlatformsRolesAndPermissions> findById(Integer id) {
        return platformsRolesAndPermissionsRepository.findById(id);
    }

    public PlatformsRolesAndPermissions save(PlatformsRolesAndPermissions user) {
        return platformsRolesAndPermissionsRepository.save(user);
    }

    public void deleteById(Integer id) {
        platformsRolesAndPermissionsRepository.deleteById(id);
    }

}
