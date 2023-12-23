package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.core.dataprovider.persistence.dao.ProjectStatusDAO;
import br.com.portifolify.domain.ProjectStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ProjectStatusDAOImpl implements ProjectStatusDAO {

    private List<ProjectStatus> inMemoryData;

    @Override
    public List<ProjectStatus> findAll() {

        if (Objects.isNull(inMemoryData)) {
            inMemoryData =  List.of(
                    ProjectStatus.builder()
                            .description("Em análise")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder()
                            .description("Análise realizada")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder()
                            .description("Análise aprovada")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder()
                            .description("Iniciado")
                            .canDelete(false)
                            .build(),
                    ProjectStatus.builder()
                            .description("Planejado")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder()
                            .description("Em andamento")
                            .canDelete(false)
                            .build(),
                    ProjectStatus.builder()
                            .description("Encerrado")
                            .canDelete(false)
                            .build(),
                    ProjectStatus.builder()
                            .description("Cancelado")
                            .canDelete(true)
                            .build()
            );
        }

        return inMemoryData;
    }

}
