package com.dolphinpay.server.rest_api.v1.users_devices;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersDevicesService {
    @NonNull
    private UsersDevicesRepository usersDevicesRepository;

    public List<UsersDevices> findAll() {
        return usersDevicesRepository.findAll();
    }

    public Optional<UsersDevices> findById(Integer id) {
        return usersDevicesRepository.findById(id);
    }

    public UsersDevices save(UsersDevices usersDevices) {
        return usersDevicesRepository.save(usersDevices);
    }

    public void deleteById(Integer id) {
        usersDevicesRepository.deleteById(id);
    }

    public UsersDevices findByUserId(int userId) {
        return usersDevicesRepository.findByUserId(userId);
    }
}
