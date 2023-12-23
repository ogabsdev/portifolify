package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.persistence.dao.ManagerDAO;
import br.com.portifolify.core.service.FindManagerService;
import br.com.portifolify.domain.Manager;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;

@Named
@RequiredArgsConstructor
public class FindManagerServiceImpl implements FindManagerService {

    private final ManagerDAO managerDAO;

    @Override
    public Manager find(String id) {
        return managerDAO.find(id);
    }

}
