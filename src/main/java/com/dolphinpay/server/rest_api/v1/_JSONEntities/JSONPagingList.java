package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONPagingList<ListType> {
    @NonNull
    private JSONLinks links;
    @NonNull
    private ListType[] list;
    @NonNull
    private Long total;
}
