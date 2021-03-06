package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONTimestamp {
    private long closureTime;
}
