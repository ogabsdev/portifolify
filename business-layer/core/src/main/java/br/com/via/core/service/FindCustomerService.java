package br.com.via.core.service;

import br.com.via.domain.Customer;

public interface FindCustomerService {

    Customer find(String uuid);

}
