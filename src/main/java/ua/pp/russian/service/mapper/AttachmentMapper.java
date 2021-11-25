package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Attachment;
import ua.pp.russian.service.dto.AttachmentDTO;

/**
 * Mapper for the entity {@link Attachment} and its DTO {@link AttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = { EventMapper.class })
public interface AttachmentMapper extends EntityMapper<AttachmentDTO, Attachment> {
    @Mapping(target = "event", source = "event", qualifiedByName = "id")
    AttachmentDTO toDto(Attachment s);
}
