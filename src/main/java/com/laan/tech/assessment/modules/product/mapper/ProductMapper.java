package com.laan.tech.assessment.modules.product.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laan.tech.assessment.modules.attachment.dto.AttachmentDto;
import com.laan.tech.assessment.modules.product.dto.ProductDto;
import com.laan.tech.assessment.modules.product.entity.Product;
import com.laan.tech.assessment.modules.product.param.ProductParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto entityToDto(Product entity);

    Product paramToEntity(ProductParam param, @MappingTarget Product entity);

    default String imagesToJson(List<AttachmentDto> images) {
        if (images == null) return null;
        try {
            return new ObjectMapper().writeValueAsString(images);
        } catch (Exception e) {
            throw new RuntimeException("Error converting images to JSON", e);
        }
    }

    default List<AttachmentDto> jsonToImages(String json) {
        if (json == null) return null;
        try {
            return new ObjectMapper().readValue(json, new TypeReference<List<AttachmentDto>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error parsing images JSON", e);
        }
    }
}
