package com.dolphinpay.server.rest_api.v1._JSONEntities;

import com.dolphinpay.server.rest_api.v1.products.Products;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JSONProductsFilter {
    private Integer stand;
    private String name;
    private Integer category;
    private Integer type;

    public static JSONProductsFilter newInstance(Integer stand, String name, Integer category, Integer type) {
        return JSONProductsFilter.builder().stand(stand).name(name).category(category).type(type).build();
    }

    public Products.Filter base() {
        return Products.Filter.builder().stand(stand).category(category).type(type).name(name).build();
    }
}
