package com.dolphinpay.server.rest_api.v1.stands_rooms;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandsRoomsService {
    @NonNull
    private StandsRoomsRepository standsRoomsRepository;

    public List<StandsRooms> findAll() {
        return standsRoomsRepository.findAll();
    }

    public Optional<StandsRooms> findById(Integer id) {
        return standsRoomsRepository.findById(id);
    }

    public StandsRooms save(StandsRooms user) {
        return standsRoomsRepository.save(user);
    }

    public void deleteById(Integer id) {
        standsRoomsRepository.deleteById(id);
    }

}