package br.com.portifolify.core.usecase;

import br.com.portifolify.core.usecase.dto.ProjectDTO;

import java.util.List;

public interface FindProjectUseCase {

    List<ProjectDTO> find(String value);

}
