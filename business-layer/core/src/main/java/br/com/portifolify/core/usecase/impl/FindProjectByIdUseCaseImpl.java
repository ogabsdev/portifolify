package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.FindProjectByIdService;
import br.com.portifolify.core.usecase.FindProjectByIdUseCase;
import br.com.portifolify.core.usecase.dto.ProjectDTO;
import br.com.portifolify.core.usecase.dto.converter.ProjectUseCaseConverter;
import br.com.portifolify.core.usecase.exception.ProjectNotFoundException;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Objects;

@Named
@Transactional
@RequiredArgsConstructor
public class FindProjectByIdUseCaseImpl implements FindProjectByIdUseCase {

    private final FindProjectByIdService findProjectByIdService;

    private final ProjectUseCaseConverter projectUseCaseConverter;

    @Override
    public ProjectDTO find(String id) {

        Project project = findProjectByIdService.find(id);

        if (Objects.isNull(project)) {
            throw new ProjectNotFoundException(String.format("Project %s not found", id));
        }

        return projectUseCaseConverter.convert(project);
    }

}
