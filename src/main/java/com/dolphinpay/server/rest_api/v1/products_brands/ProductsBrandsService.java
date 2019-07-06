package com.dolphinpay.server.rest_api.v1.products_brands;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsBrandsService {
    @NonNull
    private ProductsBrandsRepository productsBrandsRepository;

    public List<ProductsBrands> findAll() {
        return productsBrandsRepository.findAll();
    }

    public Optional<ProductsBrands> findById(Integer id) {
        return productsBrandsRepository.findById(id);
    }

    public ProductsBrands save(ProductsBrands user) {
        return productsBrandsRepository.save(user);
    }

    public void deleteById(Integer id) {
        productsBrandsRepository.deleteById(id);
    }

}
