package br.com.via.core.service;

import br.com.via.domain.Customer;

public interface CreateCustomerService {

    Customer create(Customer.CustomerBuilder builder);

}
