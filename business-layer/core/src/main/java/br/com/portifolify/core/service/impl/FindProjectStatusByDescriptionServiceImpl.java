package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectStatusDAO;
import br.com.portifolify.core.service.FindProjectStatusByDescriptionService;
import br.com.portifolify.domain.ProjectStatus;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class FindProjectStatusByDescriptionServiceImpl implements FindProjectStatusByDescriptionService {

    private final ProjectStatusDAO projectStatusDAO;

    @Override
    public ProjectStatus find(String description) {
        return projectStatusDAO.find(description);
    }

}
