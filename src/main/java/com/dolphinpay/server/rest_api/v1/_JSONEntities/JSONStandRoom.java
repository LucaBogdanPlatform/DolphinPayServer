package com.dolphinpay.server.rest_api.v1._JSONEntities;

import com.dolphinpay.server.rest_api.v1.stands.Stands;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JSONStandRoom {
    private Integer id;
    private String name;
    private Stands.JSONStands stand;
}
