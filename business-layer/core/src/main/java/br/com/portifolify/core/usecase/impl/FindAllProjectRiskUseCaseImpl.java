package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.FindAllProjectRiskService;
import br.com.portifolify.core.usecase.FindAllProjectRiskUseCase;
import br.com.portifolify.core.usecase.dto.ProjectRiskDTO;
import br.com.portifolify.core.usecase.dto.converter.ProjectRiskUseCaseConverter;
import br.com.portifolify.domain.ProjectRisk;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Transactional
@RequiredArgsConstructor
class FindAllProjectRiskUseCaseImpl implements FindAllProjectRiskUseCase {

    private final FindAllProjectRiskService findAllProjectRiskService;

    private final ProjectRiskUseCaseConverter projectRiskUseCaseConverter;

    @Override
    public List<ProjectRiskDTO> find() {

        List<ProjectRisk> projectRisks = findAllProjectRiskService.findAll();

        return projectRiskUseCaseConverter.convert(projectRisks);
    }

}
