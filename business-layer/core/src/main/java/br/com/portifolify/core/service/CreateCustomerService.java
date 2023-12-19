package br.com.portifolify.core.service;

import br.com.portifolify.domain.Customer;

public interface CreateCustomerService {

    Customer create(Customer.CustomerBuilder builder);

}
