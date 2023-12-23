package br.com.portifolify.core.service;

import br.com.portifolify.domain.Project;

import java.util.List;

public interface FindProjectService {

    List<Project> find(String value);

}
