package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Event;
import ua.pp.russian.service.dto.EventDTO;

/**
 * Mapper for the entity {@link Event} and its DTO {@link EventDTO}.
 */
@Mapper(componentModel = "spring", uses = { MemberMapper.class, LanguageMapper.class, SubjectMapper.class, TypeMapper.class })
public interface EventMapper extends EntityMapper<EventDTO, Event> {
    @Mapping(target = "organizer", source = "organizer", qualifiedByName = "id")
    @Mapping(target = "language", source = "language", qualifiedByName = "id")
    @Mapping(target = "subject", source = "subject", qualifiedByName = "id")
    @Mapping(target = "type", source = "type", qualifiedByName = "id")
    EventDTO toDto(Event s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    EventDTO toDtoId(Event event);
}
