package com.laan.tech.assessment.modules.product.serviceImpl;

import com.laan.tech.assessment.modules.attachment.dto.AttachmentDto;
import com.laan.tech.assessment.modules.attachment.entity.Attachment;
import com.laan.tech.assessment.modules.attachment.service.AttachmentService;
import com.laan.tech.assessment.modules.product.dto.ProductDto;
import com.laan.tech.assessment.modules.product.entity.Product;
import com.laan.tech.assessment.modules.product.mapper.ProductMapper;
import com.laan.tech.assessment.modules.product.param.BulkProductParam;
import com.laan.tech.assessment.modules.product.param.ProductParam;
import com.laan.tech.assessment.modules.product.repo.ProductRepository;
import com.laan.tech.assessment.modules.product.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final AttachmentService attachmentService;

    @Override
    @Transactional
    public ProductDto create(ProductParam param) {
        if (param == null) {
            throw new IllegalArgumentException("Product parameter cannot be null");
        }

        Product product = paramToEntity(param, new Product());

        String productFolder = "uploads" + File.separator + UUID.randomUUID();
        File folder = new File(productFolder);
        if (!folder.exists()) folder.mkdirs();

        if (param.getImages() != null && !param.getImages().isEmpty()) {
            try {
                List<AttachmentDto> uploadedFiles = attachmentService.uploadImages(param.getImages(), productFolder);

                List<Attachment> attachments = uploadedFiles.stream().map(dto -> {
                    Attachment file = new Attachment();
                    file.setFileName(dto.getFileName());
                    file.setOriginalFileName(dto.getOriginalFileName());
                    file.setFilePath(dto.getFilePath());
                    file.setSize(dto.getSize());
                    file.setProduct(product);
                    return file;
                }).toList();

                product.setAttachments(attachments);

            } catch (Exception e) {
                throw new RuntimeException("Failed to upload images: " + e.getMessage(), e);
            }
        }

        Product saved = productRepository.save(product);

        ProductDto savedDto = entityToDto(saved);

        try {
            attachmentService.saveProductJson(productFolder, savedDto);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save product JSON: " + e.getMessage(), e);
        }

        return savedDto;
    }



    @Override
    @Transactional
    public List<ProductDto> createBulk(BulkProductParam bulkParam) {
        List<ProductParam> params = bulkParam.getProducts();
        if (params == null || params.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be null or empty");
        }

        List<ProductDto> productDtoList = new ArrayList<>();

        for (ProductParam param : params) {
            if (param.getImages() == null) {
                param.setImages(new ArrayList<>());
            }

            ProductDto dto = create(param);
            productDtoList.add(dto);
        }

        return productDtoList;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAll(Integer page, Integer size, String sort, String direction) {
        Specification<Product> specification = Specification.allOf();

        Sort sortObj = (direction != null && direction.equalsIgnoreCase("desc"))
                ? Sort.by(sort).descending()
                : Sort.by(sort).ascending();

        return productRepository.findAll(specification, sortObj)
                .stream()
                .map(entity -> {
                    try {
                        return entityToDto(entity);
                    } catch (Exception e) {
                        throw new RuntimeException("Error mapping transaction entity to DTO", e);
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDto getById(UUID id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
        return entityToDto(entity);
    }

    @Override
    @Transactional
    public ProductDto update(UUID id, ProductParam param) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Product entity = paramToEntity(param, product);
        Product updated = productRepository.save(entity);
        return entityToDto(updated);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        product.setIsDeleted(Boolean.TRUE);
        productRepository.save(product);
    }

    private ProductDto entityToDto(Product product) {
        ProductDto dto = productMapper.entityToDto(product);
        if (product.getId() != null) {
            List<AttachmentDto> attachments = attachmentService.getByProductId(product.getId());
            dto.setFiles(attachments);
        }
        return dto;
    }

    private Product paramToEntity(ProductParam param, Product entity) {
        productMapper.paramToEntity(param, entity);
        return entity;
    }
}