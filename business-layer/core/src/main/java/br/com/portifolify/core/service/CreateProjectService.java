package br.com.portifolify.core.service;

import br.com.portifolify.domain.Project;

public interface CreateProjectService {

    Project create(Project.ProjectBuilder builder);

}
