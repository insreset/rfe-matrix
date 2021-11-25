package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Feedback;
import ua.pp.russian.service.dto.FeedbackDTO;

/**
 * Mapper for the entity {@link Feedback} and its DTO {@link FeedbackDTO}.
 */
@Mapper(componentModel = "spring", uses = { EventMapper.class })
public interface FeedbackMapper extends EntityMapper<FeedbackDTO, Feedback> {
    @Mapping(target = "event", source = "event", qualifiedByName = "id")
    FeedbackDTO toDto(Feedback s);
}
