package br.com.portifolify.core.usecase.dto.converter;

import br.com.portifolify.core.usecase.dto.ProjectRiskDTO;
import br.com.portifolify.domain.ProjectRisk;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface ProjectRiskUseCaseConverter {

    ProjectRiskDTO convert(ProjectRisk projectRisk);

    default List<ProjectRiskDTO> convert(List<ProjectRisk> projectRisks) {

        if (Objects.isNull(projectRisks)) {
            return Collections.emptyList();
        }

        return projectRisks.stream()
                .map(this::convert)
                .toList();
    }
}
