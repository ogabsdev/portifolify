package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.core.dataprovider.cryptography.Encrypt;
import br.com.portifolify.core.dataprovider.persistence.dao.ProjectStatusDAO;
import br.com.portifolify.domain.ProjectStatus;
import br.com.portifolify.domain.vo.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ProjectStatusDAOImpl implements ProjectStatusDAO {

    private final Encrypt<Long, String> encrypt;

    private List<ProjectStatus> inMemoryData;

    @Override
    public List<ProjectStatus> findAll() {

        if (Objects.isNull(inMemoryData)) {
            inMemoryData =  List.of(
                    ProjectStatus.builder()
                            .id(Id.builder().value(encrypt.value(1L)).build())
                            .description("Em análise")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder()
                            .id(Id.builder().value(encrypt.value(2L)).build())
                            .description("Análise realizada")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder().id(Id.builder()
                                    .value(encrypt.value(3L)).build())
                            .description("Análise aprovada")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder()
                            .id(Id.builder().value(encrypt.value(4L)).build())
                            .description("Iniciado")
                            .canDelete(false)
                            .build(),
                    ProjectStatus.builder()
                            .id(Id.builder().value(encrypt.value(5L)).build())
                            .description("Planejado")
                            .canDelete(true)
                            .build(),
                    ProjectStatus.builder()
                            .id(Id.builder().value(encrypt.value(6L)).build())
                            .description("Em andamento")
                            .canDelete(false)
                            .build(),
                    ProjectStatus.builder()
                            .id(Id.builder().value(encrypt.value(7L)).build())
                            .description("Encerrado")
                            .canDelete(false)
                            .build(),
                    ProjectStatus.builder()
                            .id(Id.builder().value(encrypt.value(8L)).build())
                            .description("Cancelado")
                            .canDelete(true)
                            .build()
            );
        }

        return inMemoryData;
    }

}
