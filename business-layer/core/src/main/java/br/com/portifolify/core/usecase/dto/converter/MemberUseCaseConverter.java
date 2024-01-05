package br.com.portifolify.core.usecase.dto.converter;

import br.com.portifolify.core.usecase.dto.MemberDTO;
import br.com.portifolify.domain.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface MemberUseCaseConverter {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "cpf", source = "cpf.value")
    MemberDTO convert(Member member);

    default List<MemberDTO> convert(List<Member> members) {

        if (Objects.isNull(members)) {
            return Collections.emptyList();
        }

        return members.stream()
                .map(this::convert)
                .toList();
    }
}
