package com.laan.tech.assessment.modules.product.controller;

import com.laan.tech.assessment.common.ApiProvider;
import com.laan.tech.assessment.common.ApiResponse;
import com.laan.tech.assessment.modules.product.dto.ProductDto;
import com.laan.tech.assessment.modules.product.param.BulkProductParam;
import com.laan.tech.assessment.modules.product.param.ProductParam;
import com.laan.tech.assessment.modules.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiProvider.Product.ROOT_PATH)
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> create(@ModelAttribute ProductParam param) {
        ProductDto created = productService.create(param);
        return ResponseEntity.ok(created);
    }

    @PostMapping(value = ApiProvider.Product.BULK_UPLOAD, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<ProductDto>> createBulk(@ModelAttribute BulkProductParam bulkParam) {
        List<ProductDto> created = productService.createBulk(bulkParam);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size, @RequestParam(defaultValue = "createdAt") String sort, @RequestParam(defaultValue = "desc") String direction) {
        return ResponseEntity.ok(ApiResponse.success(productService.getAll(page, size, sort, direction), "Product retrieved successfully", HttpStatus.OK));
    }

    @GetMapping(value = ApiProvider.Product.PRODUCT_IDENTIFIER)
    public ResponseEntity<ProductDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(productService.getById(id), "Product retrieved successfully", HttpStatus.OK).getData());
    }

    @PutMapping(value = ApiProvider.Product.PRODUCT_IDENTIFIER)
    public ResponseEntity<ProductDto> update(@PathVariable UUID id, @Valid @RequestBody ProductParam param) {
        return ResponseEntity.ok(ApiResponse.success(productService.update(id, param), "Product updated successfully", HttpStatus.OK).getData());
    }

    @DeleteMapping(value = ApiProvider.Product.PRODUCT_IDENTIFIER)
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Product deleted successfully", HttpStatus.OK));
    }
}