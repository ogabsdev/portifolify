package br.com.portifolify.core.service;

import br.com.portifolify.domain.Customer;

public interface CustomerInactivationService {

    void inactivate(Customer customer);

}
