package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.ProjectEntity;
import br.com.portifolify.application.dataprovider.impl.persistence.entity.converter.ProjectEntityConverter;
import br.com.portifolify.core.dataprovider.cryptography.Decrypt;
import br.com.portifolify.core.dataprovider.cryptography.Encrypt;
import br.com.portifolify.core.dataprovider.persistence.dao.ProjectDAO;
import br.com.portifolify.core.dataprovider.persistence.dao.ProjectStatusDAO;
import br.com.portifolify.domain.Project;
import br.com.portifolify.domain.ProjectStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class ProjectDAOImpl implements ProjectDAO {

    private final EntityManager entityManager;

    private final ProjectEntityConverter projectEntityConverter;

    private final Encrypt<Long, String> encrypt;

    private final Decrypt<String, Long> decrypt;

    private final ProjectStatusDAO projectStatusDAO;

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
                encrypt.value(projectEntity.getManager().getId()),
                projectEntity,
                projectStatusDAO.find(projectEntity.getProjectStatus())
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
                .map(projectEntity -> {
                    ProjectStatus projectStatus = projectStatusDAO.find(projectEntity.getProjectStatus());

                    return projectEntityConverter.convert(
                            encrypt.value(projectEntity.getId()),
                            encrypt.value(projectEntity.getManager().getId()),
                            projectEntity,
                            projectStatus
                    );
                })
                .toList();
    }

    @Override
    public Project findById(String id) {

        Long plainId = decrypt.value(id);

        ProjectEntity projectEntity = entityManager.find(ProjectEntity.class, plainId);

        if (Objects.isNull(projectEntity)) {
            return null;
        }

        String encryptedProjectId = encrypt.value(projectEntity.getId());

        String encryptedManagerId = encrypt.value(projectEntity.getManager().getId());

        return projectEntityConverter.convert(
                encryptedProjectId,
                encryptedManagerId,
                projectEntity,
                projectStatusDAO.find(projectEntity.getProjectStatus())
        );
    }

    @Override
    public void update(Project project) {
        Long projectId = decrypt.value(project.getId().getValue());

        ProjectEntity projectEntity = projectEntityConverter.convert(projectId, project);

        String encryptedProjectId = encrypt.value(projectEntity.getId());

        String encryptedManagerId = encrypt.value(projectEntity.getManager().getId());

        ProjectEntity projectEntityUpdated = entityManager.merge(projectEntity);

        projectEntityConverter.convert(
                encryptedProjectId,
                encryptedManagerId,
                projectEntityUpdated,
                projectStatusDAO.find(projectEntity.getProjectStatus())
        );
    }

    @Override
    public void delete(String id) {
        Long plainId = decrypt.value(id);

        ProjectEntity projectEntity = entityManager.find(ProjectEntity.class, plainId);

        entityManager.remove(projectEntity);
    }

}
