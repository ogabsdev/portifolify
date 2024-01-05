package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectDAO;
import br.com.portifolify.core.service.FindProjectService;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
public class FindProjectServiceImpl implements FindProjectService {

    private final ProjectDAO projectDAO;

    @Override
    public List<Project> find(String value) {
        return projectDAO.find(value);
    }

}
