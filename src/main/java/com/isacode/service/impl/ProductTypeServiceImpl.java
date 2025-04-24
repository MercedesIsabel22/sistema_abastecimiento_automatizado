package com.isacode.service.impl;

import com.isacode.entity.TypeProduct;
import com.isacode.repository.TypeProductRepository;
import com.isacode.service.ProductTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductTypeServiceImpl implements ProductTypeService {

    private final TypeProductRepository repository;

    @Override
    public List<TypeProduct> list() {
        return repository.findAll();
    }
}
