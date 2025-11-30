package com.laan.tech.assessment.modules.attachment.controller;

import com.laan.tech.assessment.common.ApiProvider;
import com.laan.tech.assessment.modules.attachment.dto.AttachmentDto;
import com.laan.tech.assessment.modules.attachment.service.AttachmentService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiProvider.File.ROOT_PATH)
@Hidden
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseEntity<List<AttachmentDto>> uploadImages(@RequestParam("images") List<MultipartFile> images, String folderPath) throws Exception {
        List<AttachmentDto> uploaded = attachmentService.uploadImages(images, folderPath );
        return ResponseEntity.ok(uploaded);
    }

    @GetMapping(value = ApiProvider.File.FILE_IDENTIFIER_BY_PRODUCT)
    public ResponseEntity<List<AttachmentDto>> getAttachmentsByProductId(@PathVariable UUID id){
        List<AttachmentDto> attachments = attachmentService.getByProductId(id);
        return ResponseEntity.ok(attachments);
    }
}
