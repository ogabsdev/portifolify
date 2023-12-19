package br.com.portifolify.core.service;

import br.com.portifolify.domain.Customer;

public interface FindCustomerService {

    Customer find(String uuid);

}
