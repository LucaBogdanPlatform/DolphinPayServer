package com.dolphinpay.server.rest_api.v1.stands_rooms;

import com.dolphinpay.server.rest_api.v1.stands.Stands;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface StandsRoomsRepository extends JpaRepository<StandsRooms, Integer> {
    Optional<StandsRooms> findByStandAndName(Stands stand, String name);

    StandsRooms[] findByStand(Stands stand);
}
