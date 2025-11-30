package com.laan.tech.assessment.modules.attachment.dto;

import com.laan.tech.assessment.common.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class AttachmentDto extends BaseDto {

    private String fileName;

    private String originalFileName;

    private long size;

    private String filePath;
}