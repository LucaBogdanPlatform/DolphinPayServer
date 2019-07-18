package com.dolphinpay.server.rest_api.v1.stands;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONLinks;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONPagingList;
import com.dolphinpay.server.rest_api.v1._JSONEntities.swagger_responses.JSONPagingListStands;
import com.dolphinpay.server.rest_api.v1.users.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;
import static com.dolphinpay.server.rest_api.v1.UtilsV1.BASE_FULL_LINK;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class StandsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StandsAPI.class);

    @NonNull
    private UserService userService;
    @NonNull
    private StandsService standsService;

    @GetMapping(UtilsV1.URLS.stands)
    @ApiOperation(
            value = "Get all available stands",
            response = JSONPagingListStands.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid token or email or offset or count param"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getAllPaged(
            @RequestParam String token,
            @RequestParam int offset,
            @RequestParam int count) {
        return checkAuthAndUser(userService, token, (user) -> {
            Pageable pageableReq = PageRequest.of(offset / count, count);
            Page<Stands> page = standsService.findAll(pageableReq);

            String genericRequest = BASE_FULL_LINK + UtilsV1.URLS.stands + "?";
            genericRequest += "token=" + token;
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

            JSONPagingList.JSONPagingListBuilder<Stands.JSONStands> response = JSONPagingList.builder();
            response.total(standsService.count());
            response.list(page.stream().map(Stands::getResponse).toArray(Stands.JSONStands[]::new));
            response.links(links.build());
            return ResponseEntity.ok(response.build());
        });
    }

}
