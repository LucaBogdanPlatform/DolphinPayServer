package com.dolphinpay.server.rest_api.v1.products_types_categories;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsTypesCategoriesService {
    @NonNull
    private ProductsTypesCategoriesRepository productsTypesCategoriesRepository;

    public List<ProductsTypesCategories> findAll() {
        return productsTypesCategoriesRepository.findAll();
    }

    public Optional<ProductsTypesCategories> findById(Integer id) {
        return productsTypesCategoriesRepository.findById(id);
    }

    public ProductsTypesCategories save(ProductsTypesCategories user) {
        return productsTypesCategoriesRepository.save(user);
    }

    public void deleteById(Integer id) {
        productsTypesCategoriesRepository.deleteById(id);
    }

    public ProductsTypesCategories[] getAllCategoriesOfRoom(Integer roomId){
        return productsTypesCategoriesRepository.getCategoriesOfRoom(roomId);
    }
}
