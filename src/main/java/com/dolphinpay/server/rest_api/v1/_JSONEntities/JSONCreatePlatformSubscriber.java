package com.dolphinpay.server.rest_api.v1._JSONEntities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONCreatePlatformSubscriber {
    private String standRoomSubscriptionCode;
    private String platformSubscriptionName;
}
