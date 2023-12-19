package br.com.via.core.service;

import br.com.via.domain.Customer;

public interface CustomerInactivationService {

    void inactivate(Customer customer);

}
