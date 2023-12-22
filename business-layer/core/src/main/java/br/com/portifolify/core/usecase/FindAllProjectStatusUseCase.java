package br.com.portifolify.core.usecase;

import br.com.portifolify.core.usecase.dto.ProjectStatusDTO;

import java.util.List;

public interface FindAllProjectStatusUseCase {

    List<ProjectStatusDTO> find();

}
