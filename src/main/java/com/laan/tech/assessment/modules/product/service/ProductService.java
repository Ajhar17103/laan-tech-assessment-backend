package com.laan.tech.assessment.modules.product.service;

import com.laan.tech.assessment.modules.product.dto.ProductDto;
import com.laan.tech.assessment.modules.product.param.BulkProductParam;
import com.laan.tech.assessment.modules.product.param.ProductParam;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductDto create(ProductParam param);

    List<ProductDto> createBulk(BulkProductParam param);

    Object getAll(Integer page, Integer size, String sort, String direction);

    ProductDto getById(UUID id);

    ProductDto update(UUID id, ProductParam param);

    void delete(UUID id);
}