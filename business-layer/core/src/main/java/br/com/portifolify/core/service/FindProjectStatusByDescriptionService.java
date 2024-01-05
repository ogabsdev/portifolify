package br.com.portifolify.core.service;

import br.com.portifolify.domain.ProjectStatus;

public interface FindProjectStatusByDescriptionService {

    ProjectStatus find(String description);

}
