package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Language;
import ua.pp.russian.service.dto.LanguageDTO;

/**
 * Mapper for the entity {@link Language} and its DTO {@link LanguageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LanguageMapper extends EntityMapper<LanguageDTO, Language> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LanguageDTO toDtoId(Language language);
}
