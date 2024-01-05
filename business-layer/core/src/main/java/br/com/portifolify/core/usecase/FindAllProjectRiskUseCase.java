package br.com.portifolify.core.usecase;

import br.com.portifolify.core.usecase.dto.ProjectRiskDTO;

import java.util.List;

public interface FindAllProjectRiskUseCase {

    List<ProjectRiskDTO> find();

}
