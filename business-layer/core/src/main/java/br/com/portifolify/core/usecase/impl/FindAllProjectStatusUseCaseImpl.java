package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.FindAllProjectStatusService;
import br.com.portifolify.core.usecase.FindAllProjectStatusUseCase;
import br.com.portifolify.core.usecase.dto.ProjectStatusDTO;
import br.com.portifolify.core.usecase.dto.converter.ProjectStatusUseCaseConverter;
import br.com.portifolify.domain.ProjectStatus;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Transactional
@RequiredArgsConstructor
class FindAllProjectStatusUseCaseImpl implements FindAllProjectStatusUseCase {

    private final FindAllProjectStatusService findAllProjectStatusService;

    private final ProjectStatusUseCaseConverter projectStatusUseCaseConverter;

    @Override
    public List<ProjectStatusDTO> find() {

        List<ProjectStatus> projectStatuses = findAllProjectStatusService.findAll();

        return projectStatusUseCaseConverter.convert(projectStatuses);
    }

}
