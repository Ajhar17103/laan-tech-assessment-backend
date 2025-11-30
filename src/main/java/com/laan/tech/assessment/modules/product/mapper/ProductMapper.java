package com.laan.tech.assessment.modules.product.mapper;

import com.laan.tech.assessment.modules.product.dto.ProductDto;
import com.laan.tech.assessment.modules.product.entity.Product;
import com.laan.tech.assessment.modules.product.param.ProductParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto entityToDto(Product entity);

    Product paramToEntity(ProductParam param, @MappingTarget Product entity);
}
