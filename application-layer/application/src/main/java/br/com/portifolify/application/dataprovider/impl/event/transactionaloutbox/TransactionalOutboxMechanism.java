package br.com.portifolify.application.dataprovider.impl.event.transactionaloutbox;

import br.com.portifolify.application.dataprovider.impl.event.record.common.DomainEventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Transactional
@RequiredArgsConstructor
public class TransactionalOutboxMechanism {

    private final EntityManager entityManager;

    private final MessageRelay messageRelay;

    private final ObjectMapper objectMapper;

    private final ApplicationEventPublisher applicationEventPublisher;

    @SneakyThrows
    @Transactional(Transactional.TxType.MANDATORY)
    public void process(TransactionalOutboxInstruction instruction, boolean publishEvent) {
        validate(instruction);
        entityManager.flush();

        var domainEventEnvelope = DomainEventEnvelope.builder()
                .eventReason(instruction.getEventReason())
                .contentVersion(instruction.getEventAssociatedEntity().getVersion())
                .content(instruction.getEventObject())
                .build();

        var eventDataString = objectMapper.writeValueAsString(domainEventEnvelope);

        Outbox outbox = instruction.getOutbox();
        outbox.setCreationDate(LocalDateTime.now());
        outbox.setEventData(eventDataString);

        entityManager.persist(outbox);

        if (publishEvent) {
            applicationEventPublisher.publishEvent(outbox);
        }
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(Outbox outbox) {
        messageRelay.publishEventAsynchronously(outbox);
    }

    private void validate(TransactionalOutboxInstruction instruction) {
        Objects.requireNonNull(instruction.getOutbox().getTopicName(), "Outbox.topicName must not be null");
    }

}
