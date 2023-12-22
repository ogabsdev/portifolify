package br.com.portifolify.core.dataprovider.persistence.dao;

import br.com.portifolify.domain.ProjectStatus;

import java.util.List;

public interface ProjectStatusDAO {

    List<ProjectStatus> findAll();

}
