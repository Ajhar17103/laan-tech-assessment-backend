package com.laan.tech.assessment.modules.product.dto;

import com.laan.tech.assessment.common.BaseDto;
import com.laan.tech.assessment.modules.attachment.dto.AttachmentDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends BaseDto {

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    private List<AttachmentDto> files;
}