package br.com.portifolify.application.entrypoint.restcontroller.dto.converter;

import br.com.portifolify.application.entrypoint.restcontroller.dto.response.MemberResponse;
import br.com.portifolify.core.usecase.dto.MemberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface MemberRestControllerConvert {

    MemberResponse convert(MemberDTO memberDTO);

    default List<MemberResponse> convert(List<MemberDTO> members) {

        if (Objects.isNull(members)) {
            return Collections.emptyList();
        }

        return members.stream()
                .map(this::convert)
                .toList();
    }

}
