package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.ProjectEntity;
import br.com.portifolify.application.dataprovider.impl.persistence.entity.converter.ProjectEntityConverter;
import br.com.portifolify.core.dataprovider.cryptography.Encrypt;
import br.com.portifolify.core.dataprovider.persistence.dao.ProjectDAO;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectDAOImpl implements ProjectDAO {

    private final EntityManager entityManager;

    private final ProjectEntityConverter projectEntityConverter;

    private final Encrypt<Long, String> encrypt;

    private static final String QUERY_FIND_PROJECTS = """
                Select p
                From ProjectEntity p
                Inner Join p.manager m
                Where 
                    p.name like :search 
                    or m.name like :search
                    or p.projectStatus like :search
                    or p.projectRisk like :search
            """;

    @Override
    public Project insert(Project project) {
        ProjectEntity projectEntity = projectEntityConverter.convert(project);

        entityManager.persist(projectEntity);

        return projectEntityConverter.convert(
                encrypt.value(projectEntity.getId()),
                projectEntity
        );
    }

    @Override
    public List<Project> find(String value) {

        TypedQuery<ProjectEntity> query = entityManager.createQuery(
                QUERY_FIND_PROJECTS,
                ProjectEntity.class
        );

        query.setParameter("search", "%" + value + "%");

        return query.getResultList()
                .stream()
                .map(projectEntity -> projectEntityConverter.convert(
                        encrypt.value(projectEntity.getId()),
                        projectEntity
                ))
                .toList();
    }

}
