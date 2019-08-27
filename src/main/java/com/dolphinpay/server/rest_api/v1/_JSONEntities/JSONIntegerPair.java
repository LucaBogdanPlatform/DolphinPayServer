package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JSONIntegerPair {
    private Integer key;
    private Integer value;
}
