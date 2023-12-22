package br.com.portifolify.core.usecase.dto.converter;

import br.com.portifolify.core.usecase.dto.ProjectStatusDTO;
import br.com.portifolify.domain.ProjectStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface ProjectStatusUseCaseConverter {

    @Mapping(target = "id", source = "id.value")
    ProjectStatusDTO convert(ProjectStatus projectStatus);

    default List<ProjectStatusDTO> convert(List<ProjectStatus> projectStatuses) {

        if (Objects.isNull(projectStatuses)) {
            return Collections.emptyList();
        }

        return projectStatuses.stream()
                .map(this::convert)
                .toList();
    }

}
