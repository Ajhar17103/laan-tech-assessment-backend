package com.laan.tech.assessment.modules.product.param;


import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BulkProductParam {
    private List<ProductParam> products;
}