package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectDAO;
import br.com.portifolify.core.service.UpdateProjectService;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class UpdateProjectServiceImpl implements UpdateProjectService {

    private final ProjectDAO projectDAO;

    @Override
    public void update(Project project) {
        projectDAO.update(project);
    }

}
