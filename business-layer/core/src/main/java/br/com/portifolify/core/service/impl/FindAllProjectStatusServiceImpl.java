package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectStatusDAO;
import br.com.portifolify.core.service.FindAllProjectStatusService;
import br.com.portifolify.domain.ProjectStatus;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
class FindAllProjectStatusServiceImpl implements FindAllProjectStatusService {

    private final ProjectStatusDAO projectStatusDAO;

    @Override
    public List<ProjectStatus> findAll() {
        return projectStatusDAO.findAll();
    }

}
