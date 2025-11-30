package com.laan.tech.assessment.modules.attachment.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.laan.tech.assessment.modules.attachment.dto.AttachmentDto;
import com.laan.tech.assessment.modules.attachment.entity.Attachment;
import com.laan.tech.assessment.modules.attachment.mapper.AttachmentMapper;
import com.laan.tech.assessment.modules.attachment.repo.AttachmentRepository;
import com.laan.tech.assessment.modules.attachment.service.AttachmentService;
import com.laan.tech.assessment.modules.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final AttachmentMapper attachmentMapper;

    @Override
    public List<AttachmentDto> uploadImages(List<MultipartFile> images, String folderPath) throws Exception {
        List<AttachmentDto> uploadedImages = new ArrayList<>();

        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdirs();

        String folderName = folder.getName();

        for (MultipartFile image : images) {
            String fileName = image.getOriginalFilename();
            File dest = new File(folder, fileName);

            try (FileOutputStream fos = new FileOutputStream(dest)) {
                fos.write(image.getBytes());
            }

            uploadedImages.add(AttachmentDto.builder().fileName(fileName).originalFileName(image.getOriginalFilename()).size(image.getSize()).filePath("/uploads/" + folderName + "/" + fileName) // ✅ relative path for frontend
                    .build());
        }
        return uploadedImages;
    }


    @Override
    public void saveProductJson(String folderPath, ProductDto productDto) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path jsonFilePath = Paths.get(folderPath, "product.json");
        mapper.writeValue(jsonFilePath.toFile(), productDto);
    }


    @Override
    @Transactional(readOnly = true)
    public List<AttachmentDto> getByProductId(UUID productId) {
        List<Attachment> attachments = attachmentRepository.findByProductId(productId);
        return attachments.stream()
                .map(this::entityToDto)
                .toList();
    }

    private AttachmentDto entityToDto(Attachment entity) {
        return attachmentMapper.entityToDto(entity);
    }

}