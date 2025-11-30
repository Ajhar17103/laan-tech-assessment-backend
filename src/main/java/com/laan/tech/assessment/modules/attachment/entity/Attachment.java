package com.laan.tech.assessment.modules.attachment.entity;

import com.laan.tech.assessment.common.BaseEntity;
import com.laan.tech.assessment.modules.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attachments")
@ToString(exclude = {"product"})
@SQLRestriction("is_deleted = false")
@EqualsAndHashCode(callSuper = true, exclude = {"product"})
@SQLDelete(sql = "UPDATE files SET is_deleted=true WHERE id=?")
public class Attachment extends BaseEntity {
    private String fileName;

    private String originalFileName;

    private long size;

    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}