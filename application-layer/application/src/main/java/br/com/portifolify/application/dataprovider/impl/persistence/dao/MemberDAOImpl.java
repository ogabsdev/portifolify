package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.PeopleEntity;
import br.com.portifolify.application.dataprovider.impl.persistence.entity.converter.PeopleManagerEntityConverter;
import br.com.portifolify.core.dataprovider.cryptography.Encrypt;
import br.com.portifolify.core.dataprovider.persistence.dao.MemberDAO;
import br.com.portifolify.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {

    private final EntityManager entityManager;

    private final PeopleManagerEntityConverter peopleEntityConverter;

    private final Encrypt<Long, String> encrypt;

    private static final String QUERY_SELECT_MEMBERS = """
                From PeopleEntity p
                Where p.isEmployee = true
            """;

    @Override
    public List<Member> findAll() {

        TypedQuery<PeopleEntity> query = entityManager.createQuery(
                QUERY_SELECT_MEMBERS,
                PeopleEntity.class
        );

        return query.getResultList()
                .stream()
                .map(peopleEntity -> peopleEntityConverter.convertToMember(
                        encrypt.value(peopleEntity.getId()),
                        peopleEntity
                ))
                .toList();
    }

}
