package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectDAO;
import br.com.portifolify.core.service.CreateProjectService;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class CreateProjectServiceImpl implements CreateProjectService {

    private final ProjectDAO projectDAO;

    @Override
    public Project create(Project.ProjectBuilder projectBuilder) {
        return projectDAO.insert(projectBuilder.build());
    }

}
