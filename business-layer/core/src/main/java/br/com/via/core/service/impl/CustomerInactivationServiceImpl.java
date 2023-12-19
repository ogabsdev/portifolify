package br.com.via.core.service.impl;

import br.com.via.core.dataprovider.event.CustomerDomainEventProducer;
import br.com.via.core.dataprovider.persistence.dao.CustomerDAO;
import br.com.via.core.service.CustomerInactivationService;
import br.com.via.domain.Customer;
import br.com.via.domain.vo.BusinessReason;
import br.com.via.domain.vo.DomainEvent;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequiredArgsConstructor
public class CustomerInactivationServiceImpl implements CustomerInactivationService {

    private final CustomerDAO customerDAO;

    private final CustomerDomainEventProducer customerDomainEventProducer;

    @Override
    public void inactivate(Customer customer) {
        customer.inactivate();

        Customer customerInactivated = customerDAO.update(customer);

        DomainEvent<Customer> domainEvent = DomainEvent.<Customer>builder()
                .content(customerInactivated)
                .eventReason(BusinessReason.CUSTOMER_INACTIVATION.getValue())
                .build();

        customerDomainEventProducer.produce(domainEvent);
    }
}
