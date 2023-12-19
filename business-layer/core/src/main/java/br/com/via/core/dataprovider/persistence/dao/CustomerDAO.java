package br.com.via.core.dataprovider.persistence.dao;

import br.com.via.domain.Customer;

public interface CustomerDAO {

    Customer insert(Customer customer);

    Customer update(Customer customer);

    Customer find(String uuid);

}
