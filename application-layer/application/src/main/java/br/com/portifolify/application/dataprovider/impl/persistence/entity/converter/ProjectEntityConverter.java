package br.com.portifolify.application.dataprovider.impl.persistence.entity.converter;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.ProjectEntity;
import br.com.portifolify.domain.Manager;
import br.com.portifolify.domain.Project;
import br.com.portifolify.domain.ProjectRisk;
import br.com.portifolify.domain.ProjectStatus;
import br.com.portifolify.domain.vo.Cpf;
import br.com.portifolify.domain.vo.Id;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface ProjectEntityConverter {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "projectRisk", source = "projectRisk.description")
    @Mapping(target = "projectStatus", source = "projectStatus.description")
    @Mapping(target = "manager.id", source = "manager.id.value")
    @Mapping(target = "manager.cpf", source = "manager.cpf.value")
    ProjectEntity convert(Project project);

    @Mapping(target = "id", source = "entityId")
    @Mapping(target = "projectRisk", source = "project.projectRisk.description")
    @Mapping(target = "projectStatus", source = "project.projectStatus.description")
    @Mapping(target = "manager.id", source = "project.manager.id.value")
    @Mapping(target = "manager.cpf", source = "project.manager.cpf.value")
    ProjectEntity convert(Long entityId, Project project);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "projectRisk.description", source = "projectRisk")
    @Mapping(target = "projectStatus.description", source = "projectStatus")
    @Mapping(target = "manager.id.value", source = "manager.id")
    @Mapping(target = "manager.cpf.value", source = "manager.cpf")
    Project convert(ProjectEntity projectEntity);

    @Mapping(target = "id.value", source = "encryptedProjectId")
    @Mapping(target = "projectRisk.description", source = "projectEntity.projectRisk")
    @Mapping(target = "projectStatus.description", source = "projectEntity.projectStatus")
    @Mapping(target = "manager.id.value", source = "encryptedManagerId")
    @Mapping(target = "manager.cpf.value", source = "projectEntity.manager.cpf")
    default Project convert(
            String encryptedProjectId,
            String encryptedManagerId,
            ProjectEntity projectEntity,
            ProjectStatus projectStatus
    ) {
        return Project.builder()
                .id(Id.builder().value(encryptedProjectId).build())
                .name(projectEntity.getName())
                .description(projectEntity.getDescription())
                .startDate(projectEntity.getStartDate())
                .expectedEndDate(projectEntity.getExpectedEndDate())
                .realEndDate(projectEntity.getRealEndDate())
                .budget(projectEntity.getBudget())
                .projectStatus(projectStatus)
                .projectRisk(ProjectRisk.builder()
                        .description(projectEntity.getProjectRisk())
                        .build())
                .manager(Manager.builder()
                        .id(Id.builder().value(encryptedManagerId).build())
                        .cpf(Cpf.builder().value(projectEntity.getManager().getCpf()).build())
                        .name(projectEntity.getManager().getName())
                        .dateOfBirth(projectEntity.getManager().getDateOfBirth())
                        .build())
                .build();
    }

}
