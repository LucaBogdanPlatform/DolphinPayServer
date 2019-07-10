package com.dolphinpay.server.rest_api.v1.platforms_partenerships;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONAuthCredentials;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONLinks;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONPagingList;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;
import static com.dolphinpay.server.rest_api.v1.UtilsV1.BASE_FULL_LINK;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class PlatformPartnershipsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PlatformPartnershipsAPI.class);

    @NonNull
    private UserService userService;
    @NonNull
    private PlatformPartnershipsService platformPartnershipsService;

    @GetMapping(UtilsV1.URLS.platformsPartnerships)
    public ResponseEntity getAllPaged(
            @RequestParam String token,
            @RequestParam String email,
            @RequestParam int offset,
            @RequestParam int count) {
        JSONAuthCredentials jsonCredentials = new JSONAuthCredentials(token, email);
        return checkAuthAndUser(userService, jsonCredentials, (user) -> {
            Pageable pageableReq = PageRequest.of(offset / count, count);
            Page<PlatformPartnerships> page = platformPartnershipsService.findAll(pageableReq);

            String genericRequest = BASE_FULL_LINK + UtilsV1.URLS.platformsPartnerships + "?";
            genericRequest += "token=" + token;
            genericRequest += "&email=" + email;
            genericRequest += "&offset=%d";
            genericRequest += "&count=%d";

            String nextRequest = page.hasNext() ? String.format(
                    genericRequest, page.nextPageable().getOffset(), page.nextPageable().getPageSize()
            ) : null;
            String prevRequest = page.hasPrevious() ? String.format(
                    genericRequest, page.previousPageable().getOffset(), page.previousPageable().getPageSize()
            ) : null;

            JSONLinks.JSONLinksBuilder links = JSONLinks.builder();
            links.prev(prevRequest);
            links.next(nextRequest);

            JSONPagingList.JSONPagingListBuilder<PlatformPartnerships> response = JSONPagingList.builder();
            response.total(platformPartnershipsService.count());
            response.list(page.getContent().toArray(new PlatformPartnerships[0]));
            response.links(links.build());
            return ResponseEntity.ok(response.build());
        });
    }
}
