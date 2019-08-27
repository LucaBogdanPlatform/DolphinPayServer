package com.dolphinpay.server.rest_api.v1.stands_rooms_categories;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandsRoomsCategoriesService {
    @NonNull
    private StandsRoomsCategoriesRepository standsRoomsCategoriesRepository;

    public List<StandsRoomsCategories> findAll() {
        return standsRoomsCategoriesRepository.findAll();
    }

    public Optional<StandsRoomsCategories> findById(StandsRoomsCategoriesIds id) {
        return standsRoomsCategoriesRepository.findById(id);
    }

    public StandsRoomsCategories save(StandsRoomsCategories standsRoomsCategories) {
        return standsRoomsCategoriesRepository.save(standsRoomsCategories);
    }

    public void deleteById(StandsRoomsCategoriesIds id) {
        standsRoomsCategoriesRepository.deleteById(id);
    }

}
