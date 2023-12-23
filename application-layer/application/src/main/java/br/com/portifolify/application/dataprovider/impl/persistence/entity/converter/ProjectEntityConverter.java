package br.com.portifolify.application.dataprovider.impl.persistence.entity.converter;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.ProjectEntity;
import br.com.portifolify.domain.Project;
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

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "projectRisk.description", source = "projectRisk")
    @Mapping(target = "projectStatus.description", source = "projectStatus")
    @Mapping(target = "manager.id.value", source = "manager.id")
    @Mapping(target = "manager.cpf.value", source = "manager.cpf")
    Project convert(ProjectEntity projectEntity);

    @Mapping(target = "id.value", source = "encryptedId")
    @Mapping(target = "projectRisk.description", source = "projectEntity.projectRisk")
    @Mapping(target = "projectStatus.description", source = "projectEntity.projectStatus")
    @Mapping(target = "manager.id.value", source = "projectEntity.manager.id")
    @Mapping(target = "manager.cpf.value", source = "projectEntity.manager.cpf")
    Project convert(String encryptedId, ProjectEntity projectEntity);

}
