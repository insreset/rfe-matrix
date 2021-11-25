package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Schedule;
import ua.pp.russian.service.dto.ScheduleDTO;

/**
 * Mapper for the entity {@link Schedule} and its DTO {@link ScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = { EventMapper.class })
public interface ScheduleMapper extends EntityMapper<ScheduleDTO, Schedule> {
    @Mapping(target = "event", source = "event", qualifiedByName = "id")
    ScheduleDTO toDto(Schedule s);
}
