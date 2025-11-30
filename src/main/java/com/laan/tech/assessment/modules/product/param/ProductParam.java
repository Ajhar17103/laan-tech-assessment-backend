package com.laan.tech.assessment.modules.product.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductParam {

    @Schema(hidden = true)
    private UUID id;

    @NotBlank(message = "name is required")
    @Schema(example = "iPhone 17 Pro Max", description = "Name for product")
    private String name;

    @NotBlank(message = "description is required")
    @Schema(example = "Brand new phone with 12GB RAM 256GB ROM", description = "Description for product")
    private String description;

    @NotNull(message = "Price is required")
    @Schema(example = "450.75", description = "Price for product")
    private Double price;

    @NotNull(message = "stock is required")
    @Schema(example = "100", description = "Stock for product")
    private Integer stock;

    private List<MultipartFile> images;
}