package com.dolphinpay.server.rest_api.v1.products;

import com.dolphinpay.server.rest_api.v1.UtilsV1;
import com.dolphinpay.server.rest_api.v1._JSONEntities.JSONProductsFilter;
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
import org.springframework.web.bind.annotation.*;

import static com.dolphinpay.server.rest_api.utils.GoogleUtils.checkAuthAndUser;
import static com.dolphinpay.server.rest_api.v1.UtilsV1.BASE_FULL_LINK;

@RestController
@RequestMapping(UtilsV1.BASE_URL)
@Slf4j
@RequiredArgsConstructor
public class ProductsAPI {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ProductsAPI.class);

    @NonNull
    private UserService userService;
    @NonNull
    private ProductsService productsService;

    @GetMapping(UtilsV1.URLS.standsProducts)
    @ApiOperation(
            value = "Get all stand's products",
            response = JSONPagingListStands.class
    )
    @ApiResponses(
            value = {
                    @ApiResponse(code = 400, message = "Invalid stand id or offset or count"),
                    @ApiResponse(code = 401, message = "Token elapsed time")
            }
    )
    public ResponseEntity getAllPaged(
            @RequestParam String token,
            @PathVariable("id") Integer standId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false) Integer type,
            @RequestParam int offset,
            @RequestParam int count) {
        return checkAuthAndUser(userService, token, (user) -> {
            JSONProductsFilter filter = JSONProductsFilter.newInstance(standId, name, category, type);
            Pageable pageableReq = PageRequest.of(offset / count, count);
            Page<Products> page = productsService.findAll(filter.base(), pageableReq);

            String genericRequest = BASE_FULL_LINK + UtilsV1.URLS.stands + "?";
            genericRequest += "token=" + token;
            genericRequest += "&name=%s";
            genericRequest += "&category=%d";
            genericRequest += "&type=%d";
            genericRequest += "&offset=%d";
            genericRequest += "&count=%d";
//
//            String nextRequest = page.hasNext() ? String.format(
//                    genericRequest, page.nextPageable().getOffset(), page.nextPageable().getPageSize()
//            ) : null;
//            String prevRequest = page.hasPrevious() ? String.format(
//                    genericRequest, page.previousPageable().getOffset(), page.previousPageable().getPageSize()
//            ) : null;
//
//            JSONLinks.JSONLinksBuilder links = JSONLinks.builder();
//            links.prev(prevRequest);
//            links.next(nextRequest);
//
//            JSONPagingList.JSONPagingListBuilder<Stands.JSONStands> response = JSONPagingList.builder();
//            response.total(standsService.count());
//            response.list(page.stream().map(Stands::getResponse).toArray(Stands.JSONStands[]::new));
//            response.links(links.build());
            return ResponseEntity.ok(null);
        });
    }

}
