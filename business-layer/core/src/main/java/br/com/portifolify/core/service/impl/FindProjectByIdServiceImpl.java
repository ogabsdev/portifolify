package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectDAO;
import br.com.portifolify.core.service.FindProjectByIdService;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class FindProjectByIdServiceImpl implements FindProjectByIdService {

    private final ProjectDAO projectDAO;

    @Override
    public Project find(String id) {
        return projectDAO.findById(id);
    }

}
