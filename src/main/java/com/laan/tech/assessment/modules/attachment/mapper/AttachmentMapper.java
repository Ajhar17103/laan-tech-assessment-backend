package com.laan.tech.assessment.modules.attachment.mapper;

import com.laan.tech.assessment.modules.attachment.dto.AttachmentDto;
import com.laan.tech.assessment.modules.attachment.entity.Attachment;
import com.laan.tech.assessment.modules.attachment.param.AttachmentParam;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {

    AttachmentDto entityToDto(Attachment entity);

    Attachment paramToEntity(AttachmentParam param, @MappingTarget Attachment entity);


}
