package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ManagerDAO;
import br.com.portifolify.core.service.FindAllManagerService;
import br.com.portifolify.domain.Manager;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import java.util.List;

@Named
@RequiredArgsConstructor
class FindAllManagerServiceImpl implements FindAllManagerService {

    private final ManagerDAO managerDAO;

    @Override
    public List<Manager> findAll() {
        return managerDAO.findAll();
    }

}
