package com.dolphinpay.server.rest_api.v1.platforms_roles;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlatformsRolesService {
    @NonNull
    private PlatformsRolesRepository platformsRolesRepository;

    public List<PlatformsRoles> findAll() {
        return platformsRolesRepository.findAll();
    }

    public Optional<PlatformsRoles> findById(Integer id) {
        return platformsRolesRepository.findById(id);
    }

    public PlatformsRoles save(PlatformsRoles user) {
        return platformsRolesRepository.save(user);
    }

    public void deleteById(Integer id) {
        platformsRolesRepository.deleteById(id);
    }

}
