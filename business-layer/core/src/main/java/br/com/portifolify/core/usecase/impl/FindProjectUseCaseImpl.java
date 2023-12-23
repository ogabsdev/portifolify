package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.FindProjectService;
import br.com.portifolify.core.usecase.FindProjectUseCase;
import br.com.portifolify.core.usecase.dto.ProjectDTO;
import br.com.portifolify.core.usecase.dto.converter.ProjectUseCaseConverter;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Transactional
@RequiredArgsConstructor
public class FindProjectUseCaseImpl implements FindProjectUseCase {

    private final FindProjectService findProjectService;

    private final ProjectUseCaseConverter projectUseCaseConverter;

    @Override
    public List<ProjectDTO> find(String value) {

        List<Project> projects = findProjectService.find(value);

        return projectUseCaseConverter.convert(projects);
    }
}
