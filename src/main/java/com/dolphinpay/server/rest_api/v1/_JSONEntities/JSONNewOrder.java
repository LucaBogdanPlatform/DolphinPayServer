package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONNewOrder {
    private JSONPair[] products;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class JSONPair {
        private Integer productId;
        private Integer productQuantity;
    }
}
