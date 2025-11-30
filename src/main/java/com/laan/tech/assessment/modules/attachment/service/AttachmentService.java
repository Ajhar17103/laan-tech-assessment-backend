package com.laan.tech.assessment.modules.attachment.service;

import com.laan.tech.assessment.modules.attachment.dto.AttachmentDto;
import com.laan.tech.assessment.modules.product.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface AttachmentService {
    List<AttachmentDto> uploadImages(List<MultipartFile> images,String folderPath) throws Exception;
    void saveProductJson(String folderPath, ProductDto productDto) throws Exception;
    List<AttachmentDto> getByProductId(UUID productId);
}
