package br.com.portifolify.application.entrypoint.webcontroller.dto.converter;

import br.com.portifolify.application.entrypoint.webcontroller.dto.request.StoreProjectRequest;
import br.com.portifolify.application.entrypoint.webcontroller.dto.request.UpdateProjectRequest;
import br.com.portifolify.core.usecase.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface ProjectWebControllerConverter {

    @Mapping(target = "projectRisk", source = "projectRiskId")
    @Mapping(target = "projectStatus", source = "projectStatusId")
    ProjectDTO convert(StoreProjectRequest request);

    @Mapping(target = "projectRiskId", source = "projectRisk")
    @Mapping(target = "projectStatusId", source = "projectStatus")
    UpdateProjectRequest convert(ProjectDTO projectDTO);

}
