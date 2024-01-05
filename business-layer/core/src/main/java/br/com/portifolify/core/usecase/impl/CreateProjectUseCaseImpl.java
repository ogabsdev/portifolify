package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.CreateProjectService;
import br.com.portifolify.core.service.FindManagerService;
import br.com.portifolify.core.service.FindProjectStatusByDescriptionService;
import br.com.portifolify.core.usecase.CreateProjectUseCase;
import br.com.portifolify.core.usecase.dto.ProjectDTO;
import br.com.portifolify.core.usecase.dto.converter.ProjectUseCaseConverter;
import br.com.portifolify.core.usecase.exception.ManagerNotFoundException;
import br.com.portifolify.domain.Manager;
import br.com.portifolify.domain.Project;
import br.com.portifolify.domain.ProjectStatus;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Objects;

@Named
@Transactional
@RequiredArgsConstructor
public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final FindManagerService findManagerService;

    private final CreateProjectService createProjectService;

    private final ProjectUseCaseConverter projectUseCaseConverter;

    private final FindProjectStatusByDescriptionService findProjectStatusByDescriptionService;

    @Override
    public ProjectDTO create(ProjectDTO projectDTO) {
        Manager manager = findManagerService.find(projectDTO.getManagerId());

        if (Objects.isNull(manager)) {
            throw new ManagerNotFoundException(String.format("Manager %s not found", projectDTO.getManagerId()));
        }

        ProjectStatus projectStatus = findProjectStatusByDescriptionService.find(projectDTO.getProjectStatus());

        Project.ProjectBuilder projectBuilder = projectUseCaseConverter.convert(projectDTO, projectStatus, manager);

        Project project = createProjectService.create(projectBuilder);

        return projectUseCaseConverter.convert(project);
    }

}
