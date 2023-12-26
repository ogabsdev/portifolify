package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.FindAllManagerService;
import br.com.portifolify.core.usecase.FindAllManagerUseCase;
import br.com.portifolify.core.usecase.dto.ManagerDTO;
import br.com.portifolify.core.usecase.dto.converter.ManagerUseCaseConverter;
import br.com.portifolify.domain.Manager;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Transactional
@RequiredArgsConstructor
class FindAllManagerUseCaseImpl implements FindAllManagerUseCase {

    private final FindAllManagerService findAllManagerService;

    private final ManagerUseCaseConverter managerUseCaseConverter;

    @Override
    public List<ManagerDTO> find() {

        List<Manager> managers = findAllManagerService.findAll();

        return managerUseCaseConverter.convert(managers);
    }

}
