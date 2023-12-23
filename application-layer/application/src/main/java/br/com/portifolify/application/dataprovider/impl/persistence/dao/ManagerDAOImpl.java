package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.PeopleEntity;
import br.com.portifolify.application.dataprovider.impl.persistence.entity.converter.PeopleManagerEntityConverter;
import br.com.portifolify.core.dataprovider.cryptography.Decrypt;
import br.com.portifolify.core.dataprovider.cryptography.Encrypt;
import br.com.portifolify.core.dataprovider.persistence.dao.ManagerDAO;
import br.com.portifolify.domain.Manager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ManagerDAOImpl implements ManagerDAO {

    private final EntityManager entityManager;

    private final PeopleManagerEntityConverter peopleEntityConverter;

    private final Encrypt<Long, String> encrypt;

    private final Decrypt<String, Long> decrypt;

    private static final String QUERY_SELECT_MANAGERS = """
                From PeopleEntity p
                Where p.isManager = true
            """;

    @Override
    public List<Manager> findAll() {

        TypedQuery<PeopleEntity> query = entityManager.createQuery(
                QUERY_SELECT_MANAGERS,
                PeopleEntity.class
        );

        return query.getResultList()
                .stream()
                .map(peopleEntity -> peopleEntityConverter.convert(
                        encrypt.value(peopleEntity.getId()),
                        peopleEntity
                ))
                .toList();
    }

    @Override
    public Manager find(String id) {
        Long managerId = decrypt.value(id);

        PeopleEntity managerEntity = entityManager.find(PeopleEntity.class, managerId);

        return peopleEntityConverter.convert(managerEntity);
    }

}
