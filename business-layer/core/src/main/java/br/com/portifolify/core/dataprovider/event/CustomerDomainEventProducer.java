package br.com.portifolify.core.dataprovider.event;

import br.com.portifolify.domain.Customer;
import br.com.portifolify.domain.vo.DomainEvent;

public interface CustomerDomainEventProducer {

    void produce(DomainEvent<Customer> customerDomainEvent);

}
