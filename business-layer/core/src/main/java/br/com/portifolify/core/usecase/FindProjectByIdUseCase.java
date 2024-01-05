package br.com.portifolify.core.usecase;

import br.com.portifolify.core.usecase.dto.ProjectDTO;

public interface FindProjectByIdUseCase {

    ProjectDTO find(String id);

}
