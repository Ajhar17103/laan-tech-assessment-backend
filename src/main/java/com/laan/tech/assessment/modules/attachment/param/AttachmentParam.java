package com.laan.tech.assessment.modules.attachment.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentParam {

    @Schema(hidden = true)
    private UUID id;

    private List<MultipartFile> images;

}