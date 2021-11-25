package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Subject;
import ua.pp.russian.service.dto.SubjectDTO;

/**
 * Mapper for the entity {@link Subject} and its DTO {@link SubjectDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SubjectDTO toDtoId(Subject subject);
}
