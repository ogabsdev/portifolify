package br.com.portifolify.core.usecase.dto.converter;

import br.com.portifolify.core.usecase.dto.ProjectDTO;
import br.com.portifolify.domain.Manager;
import br.com.portifolify.domain.Project;
import br.com.portifolify.domain.ProjectRisk;
import br.com.portifolify.domain.ProjectStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface ProjectUseCaseConverter {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "managerId", source = "manager.id.value")
    @Mapping(target = "managerName", source = "manager.name")
    @Mapping(target = "projectRisk", source = "projectRisk.description")
    @Mapping(target = "projectStatus", source = "projectStatus.description")
    ProjectDTO convert(Project project);


    default Project.ProjectBuilder convert(ProjectDTO project, Manager manager) {
        return Project.builder()
                .name(project.getName())
                .startDate(project.getStartDate())
                .expectedEndDate(project.getExpectedEndDate())
                .budget(project.getBudget())
                .description(project.getDescription())
                .projectRisk(ProjectRisk.builder().description(project.getProjectRisk()).build())
                .projectStatus(ProjectStatus.builder().description(project.getProjectStatus()).build())
                .manager(manager);
    }

    default List<ProjectDTO> convert(List<Project> projects) {

        if (Objects.isNull(projects)) {
            return Collections.emptyList();
        }

        return projects.stream()
                .map(this::convert)
                .toList();
    }
}
