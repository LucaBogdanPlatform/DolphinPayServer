package com.dolphinpay.server.rest_api.v1.products;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    @NonNull
    private ProductsRepository productsRepository;

    public List<Products> findAll() {
        return productsRepository.findAll();
    }

    public Page<Products> findAll(Products.Filter filter, Pageable pageable) {


        return null;
    }

    public Optional<Products> findById(Integer id) {
        return productsRepository.findById(id);
    }

    public Products save(Products products) {
        return productsRepository.save(products);
    }

    public void deleteById(Integer id) {
        productsRepository.deleteById(id);
    }
}
