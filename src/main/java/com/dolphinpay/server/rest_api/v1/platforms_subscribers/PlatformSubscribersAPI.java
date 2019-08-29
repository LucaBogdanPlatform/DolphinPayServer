package com.dolphinpay.server.rest_api.v1.platforms_subscribers;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONCreatePlatformSubscriber;
import com.dolphinpay.server.rest_api.v1.platforms_permissions.PlatformsPermissionsService;
import com.dolphinpay.server.rest_api.v1.platforms_roles.PlatformsRolesService;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRooms;
import com.dolphinpay.server.rest_api.v1.stands_rooms.StandsRoomsService;
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

import java.util.Optional;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class PlatformSubscribersAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlatformSubscribersAPI.class);

    @NonNull
    private UserService userService;
    @NonNull
    private PlatformSubscribersService service;
    @NonNull
    private StandsRoomsService standsRoomsService;
    @NonNull
    private PlatformsRolesService platformsRolesService;
    @NonNull
    private PlatformsPermissionsService platformsPermissionsService;

    @Transactional
    @DeleteMapping(UtilsV1.URLS.deletePlatformSubscriber)
    @ApiOperation(
            value = "Delete a specific user platform subscriber"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity deleteCategoryFromRoom(
            @PathVariable Integer platformId,
            @RequestParam String token) {
        return checkAuthAndUser(userService, token, (user) -> {
            Optional<PlatformSubscribers> platformSubscribers = service.findByUserAndId(user, platformId);

            if (!platformSubscribers.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            service.deleteById(platformSubscribers.get().getId());
            return ResponseEntity.ok(HttpStatus.OK);
        });
    }


    @Transactional
    @PostMapping(UtilsV1.URLS.createSubscriptionPlatform)
    @ApiOperation(
            value = "Create new subscription platform"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity createPlatformSubscriber(@RequestParam String token,
                                                   @RequestBody JSONCreatePlatformSubscriber data) {
        return checkAuthAndUser(userService, token, (user) -> {
            if (data == null || data.getPlatformSubscriptionName() == null || data.getStandRoomSubscriptionCode() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            Optional<StandsRooms> standsRoom = standsRoomsService.findBySubscriptionCode(data.getStandRoomSubscriptionCode());

            if (!standsRoom.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            PlatformSubscribers newPlatform = service.save(
                    PlatformSubscribers.builder()
                            .name(data.getPlatformSubscriptionName())
                            .room(standsRoom.get())
                            .user(user)
                            .build()
            );

            newPlatform.setRole(platformsRolesService.findById(2).get());

            return ResponseEntity.ok(newPlatform.getHttpResponseStandard(platformsPermissionsService.findRolePermissions(newPlatform.getRole().getId())));
        });
    }
}
