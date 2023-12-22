package br.com.portifolify.core.usecase;

import br.com.portifolify.core.usecase.dto.ManagerDTO;

import java.util.List;

public interface FindAllManagerUseCase {

    List<ManagerDTO> find();

}
