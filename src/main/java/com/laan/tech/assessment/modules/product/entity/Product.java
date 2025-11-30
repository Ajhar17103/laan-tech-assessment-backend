package com.laan.tech.assessment.modules.product.entity;

import com.laan.tech.assessment.common.BaseEntity;
import com.laan.tech.assessment.modules.attachment.entity.Attachment;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
@EqualsAndHashCode(callSuper = true)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE products SET is_deleted=true WHERE id=?")
public class Product extends BaseEntity {

    private String name;

    private String description;

    private Double price;

    private Integer stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments = new ArrayList<>();
}
