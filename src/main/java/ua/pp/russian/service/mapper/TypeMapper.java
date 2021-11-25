package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Type;
import ua.pp.russian.service.dto.TypeDTO;

/**
 * Mapper for the entity {@link Type} and its DTO {@link TypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeMapper extends EntityMapper<TypeDTO, Type> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeDTO toDtoId(Type type);
}
