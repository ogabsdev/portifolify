package br.com.portifolify.core.dataprovider.persistence.dao;

import br.com.portifolify.domain.Project;

import java.util.List;

public interface ProjectDAO {

    Project insert(Project project);

    List<Project> find(String value);

}
