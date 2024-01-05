package br.com.portifolify.core.dataprovider.persistence.dao;

import br.com.portifolify.domain.Manager;

import java.util.List;

public interface ManagerDAO {

    List<Manager> findAll();

    Manager find(String id);

}
