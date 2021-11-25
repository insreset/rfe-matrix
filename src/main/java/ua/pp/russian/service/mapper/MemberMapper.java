package ua.pp.russian.service.mapper;

import org.mapstruct.*;
import ua.pp.russian.domain.Member;
import ua.pp.russian.service.dto.MemberDTO;

/**
 * Mapper for the entity {@link Member} and its DTO {@link MemberDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MemberMapper extends EntityMapper<MemberDTO, Member> {
    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MemberDTO toDtoId(Member member);
}
