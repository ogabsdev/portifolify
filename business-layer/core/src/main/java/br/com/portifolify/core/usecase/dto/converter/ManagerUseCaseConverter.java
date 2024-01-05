package br.com.portifolify.core.usecase.dto.converter;

import br.com.portifolify.core.usecase.dto.ManagerDTO;
import br.com.portifolify.domain.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface ManagerUseCaseConverter {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "cpf", source = "cpf.value")
    ManagerDTO convert(Manager manager);

    default List<ManagerDTO> convert(List<Manager> managers) {

        if (Objects.isNull(managers)) {
            return Collections.emptyList();
        }

        return managers.stream()
                .map(this::convert)
                .toList();
    }
}
