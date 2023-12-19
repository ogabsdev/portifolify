package br.com.portifolify.application.dataprovider.impl.event.producer;

import br.com.portifolify.application.dataprovider.impl.event.producer.converter.CustomerDomainEventConverter;
import br.com.portifolify.application.dataprovider.impl.event.record.CustomerDomainEventRecord;
import br.com.portifolify.application.dataprovider.impl.event.transactionaloutbox.TransactionalOutboxInstruction;
import br.com.portifolify.application.dataprovider.impl.event.transactionaloutbox.TransactionalOutboxMechanism;
import br.com.portifolify.application.dataprovider.impl.persistence.entity.CustomerEntity;
import br.com.portifolify.application.dataprovider.impl.persistence.entity.CustomerOutboxEntity;
import br.com.portifolify.core.dataprovider.event.CustomerDomainEventProducer;
import br.com.portifolify.domain.Customer;
import br.com.portifolify.domain.vo.DomainEvent;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerDomainEventProducerImpl implements CustomerDomainEventProducer {

    private final EntityManager entityManager;

    private final TransactionalOutboxMechanism transactionalOutboxMechanism;

    private final CustomerDomainEventConverter customerDomainEventConverter;

    @Value(value = "${kafka.topics.customer-domain-event.name}")
    private String customerDomainEventTopicName;

    @Override
    @SneakyThrows
    public void produce(DomainEvent<Customer> customerDomainEvent) {
        Customer customer = customerDomainEvent.getContent();
        CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customer.getUuid());
        CustomerDomainEventRecord record = customerDomainEventConverter.convert(customer);

        var instruction = TransactionalOutboxInstruction.builder()
                .eventObject(record)
                .eventReason(customerDomainEvent.getEventReason())
                .eventAssociatedEntity(customerEntity)
                .outbox(new CustomerOutboxEntity(customerDomainEventTopicName))
                .build();

        transactionalOutboxMechanism.process(instruction, true);
    }

}
