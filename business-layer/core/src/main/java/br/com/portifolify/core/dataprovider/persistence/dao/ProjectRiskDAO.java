package br.com.portifolify.core.dataprovider.persistence.dao;

import br.com.portifolify.domain.ProjectRisk;

import java.util.List;

public interface ProjectRiskDAO {

    List<ProjectRisk> findAll();

}
