package com.dolphinpay.server.rest_api.v1._JSONEntities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONLinks {
    @Nullable
    private String next;
    @Nullable
    private String prev;

}
