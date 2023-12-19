package br.com.via.core.dataprovider.event;

import br.com.via.domain.Customer;
import br.com.via.domain.vo.DomainEvent;

public interface CustomerDomainEventProducer {

    void produce(DomainEvent<Customer> customerDomainEvent);

}
