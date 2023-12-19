package br.com.portifolify.application.dataprovider.impl.persistence.dao;

import br.com.portifolify.application.dataprovider.impl.event.transactionaloutbox.Outbox;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OutboxDAOImpl {

    private final EntityManager entityManager;

    public void update(Outbox outbox) {
        entityManager.merge(outbox);
    }

}
