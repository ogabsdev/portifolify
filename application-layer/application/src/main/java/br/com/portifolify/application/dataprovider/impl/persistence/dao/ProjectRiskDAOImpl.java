package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectRiskDAO;
import br.com.portifolify.domain.ProjectRisk;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ProjectRiskDAOImpl implements ProjectRiskDAO {

    private List<ProjectRisk> inMemoryData;

    @Override
    public List<ProjectRisk> findAll() {

        if (Objects.isNull(inMemoryData)) {
            inMemoryData = List.of(
                    ProjectRisk.builder()
                            .description("Baixo")
                            .build(),
                    ProjectRisk.builder()
                            .description("Médio")
                            .build(),
                    ProjectRisk.builder()
                            .description("Alto")
                            .build()
            );
        }

        return inMemoryData;
    }

}
