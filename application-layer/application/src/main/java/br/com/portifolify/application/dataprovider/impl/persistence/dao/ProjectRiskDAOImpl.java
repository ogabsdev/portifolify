package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.core.dataprovider.cryptography.Encrypt;
import br.com.portifolify.core.dataprovider.persistence.dao.ProjectRiskDAO;
import br.com.portifolify.domain.ProjectRisk;
import br.com.portifolify.domain.vo.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ProjectRiskDAOImpl implements ProjectRiskDAO {

    private final Encrypt<Long, String> encrypt;

    private List<ProjectRisk> inMemoryData;

    @Override
    public List<ProjectRisk> findAll() {

        if (Objects.isNull(inMemoryData)) {
            inMemoryData = List.of(
                    ProjectRisk.builder()
                            .id(Id.builder().value(encrypt.value(1L)).build())
                            .description("Baixo")
                            .build(),
                    ProjectRisk.builder()
                            .id(Id.builder().value(encrypt.value(2L)).build())
                            .description("Médio")
                            .build(),
                    ProjectRisk.builder()
                            .id(Id.builder().value(encrypt.value(3L)).build())
                            .description("Alto")
                            .build()
            );
        }

        return inMemoryData;
    }

}
