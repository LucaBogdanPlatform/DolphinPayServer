package com.dolphinpay.server.rest_api.v1.products_types;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsTypesService {
    @NonNull
    private ProductsTypesRepository productsTypesRepository;

    public List<ProductsTypes> findAll() {
        return productsTypesRepository.findAll();
    }

    public Optional<ProductsTypes> findById(Integer id) {
        return productsTypesRepository.findById(id);
    }

    public ProductsTypes save(ProductsTypes productsTypes) {
        return productsTypesRepository.save(productsTypes);
    }

    public void deleteById(Integer id) {
        productsTypesRepository.deleteById(id);
    }
}
