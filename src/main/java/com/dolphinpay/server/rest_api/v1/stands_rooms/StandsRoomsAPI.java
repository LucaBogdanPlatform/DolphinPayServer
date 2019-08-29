package com.dolphinpay.server.rest_api.v1.stands_rooms;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONLink;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONStandRoom;
import com.dolphinpay.server.rest_api.v1.platforms_partenerships.PlatformPartnerships;
import com.dolphinpay.server.rest_api.v1.platforms_partenerships.PlatformPartnershipsService;
import com.dolphinpay.server.rest_api.v1.stands.Stands;
import com.dolphinpay.server.rest_api.v1.stands.StandsService;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class StandsRoomsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StandsRoomsAPI.class);

    @NonNull
    private StandsRoomsService service;
    @NonNull
    private PlatformPartnershipsService platformPartnershipsService;
    @NonNull
    private StandsService standsService;
    @NonNull
    private UserService userService;


    @Transactional
    @PostMapping(UtilsV1.URLS.createRoomForStand)
    @ApiOperation(
            value = "Create new room for specific stand"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity addCategoryToRoom(@RequestParam String token,
                                            @RequestBody JSONStandRoom data) {
        return checkAuthAndUser(userService, token, (user) -> {


            Optional<Stands> stand = standsService.findById(data.getStand().getId());

            if (!stand.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            } else {
                Optional<PlatformPartnerships> s = platformPartnershipsService.findByStandAndUser(stand.get(), user);
                if (!s.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                }
            }

            Optional<StandsRooms> room = service.findByStandAndName(stand.get(), data.getName());

            if (room.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            return ResponseEntity.ok(
                    service.save(StandsRooms.builder()
                            .name(data.getName())
                            .stand(stand.get())
                            .subscriptionCode(UUID.randomUUID().toString())
                            .build()
                    ).getHttpResponseStandard()
            );
        });
    }


    @GetMapping(UtilsV1.URLS.standsRooms)
    @ApiOperation(
            value = "Get all rooms of specific stand"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getAllRoomsOfStand(
            @RequestParam String token,
            @PathVariable("id") Integer standId) {
        return checkAuthAndUser(userService, token, (user) -> {

            Optional<Stands> stand = standsService.findById(standId);

            if (!stand.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            ArrayList<JSONStandRoom> response = new ArrayList<>();
            StandsRooms[] standRooms = service.findByStand(stand.get());

            for (StandsRooms sr : standRooms) {
                response.add(sr.getHttpResponseStandard());
            }

            return ResponseEntity.ok(response);
        });
    }

    @GetMapping(UtilsV1.URLS.roomSubscriptionCode)
    @ApiOperation(
            value = "Return the url of the QR-Code built with subscribed code"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getSubscriptionCode(
            @RequestParam String token,
            @PathVariable("id") Integer roomId) {
        return checkAuthAndUser(userService, token, (user) -> {

            Optional<StandsRooms> room = service.findById(roomId);

            if (!room.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            room.get().setSubscriptionCode(
                    UUID.fromString(room.get().getId() + UUID.randomUUID().toString()).toString()
            );

            return ResponseEntity.ok(JSONLink.builder().url(service.save(room.get()).getSubscriptionCode()).build());
        });
    }
}
