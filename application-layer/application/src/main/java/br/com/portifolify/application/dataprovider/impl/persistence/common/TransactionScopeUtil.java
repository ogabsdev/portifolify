package br.com.portifolify.application.dataprovider.impl.persistence.common;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.common.VersionableEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Transactional(Transactional.TxType.MANDATORY)
public class TransactionScopeUtil {

    private final EntityManager entityManager;

    public Long getCurrentVersion(Class<? extends VersionableEntity> entityClass, Object primaryKey) {
        return entityManager.getReference(entityClass, primaryKey).getVersion();
    }

}
