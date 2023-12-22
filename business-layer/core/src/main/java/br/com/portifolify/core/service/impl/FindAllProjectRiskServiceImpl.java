package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectRiskDAO;
import br.com.portifolify.core.service.FindAllProjectRiskService;
import br.com.portifolify.domain.ProjectRisk;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
class FindAllProjectRiskServiceImpl implements FindAllProjectRiskService {

    private final ProjectRiskDAO projectRiskDAO;

    @Override
    public List<ProjectRisk> findAll() {
        return projectRiskDAO.findAll();
    }

}
