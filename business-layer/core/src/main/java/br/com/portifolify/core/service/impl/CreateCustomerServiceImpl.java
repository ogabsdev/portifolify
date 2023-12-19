package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.event.CustomerDomainEventProducer;
import br.com.portifolify.core.dataprovider.persistence.dao.CustomerDAO;
import br.com.portifolify.core.service.CreateCustomerService;
import br.com.portifolify.domain.Customer;
import br.com.portifolify.domain.vo.BusinessReason;
import br.com.portifolify.domain.vo.DomainEvent;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequiredArgsConstructor
public class CreateCustomerServiceImpl implements CreateCustomerService {

    private final CustomerDAO customerDAO;

    private final CustomerDomainEventProducer customerDomainEventProducer;

    @Override
    public Customer create(Customer.CustomerBuilder customerBuilder) {
        Customer customer = customerDAO.insert(customerBuilder.build());

        DomainEvent<Customer> domainEvent = DomainEvent.<Customer>builder()
                .content(customer)
                .eventReason(BusinessReason.CUSTOMER_REGISTER.getValue())
                .build();

        customerDomainEventProducer.produce(domainEvent);

        return customer;
    }

}
