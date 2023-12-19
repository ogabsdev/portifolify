package br.com.portifolify.core.dataprovider.persistence.dao;

import br.com.portifolify.domain.Customer;

public interface CustomerDAO {

    Customer insert(Customer customer);

    Customer update(Customer customer);

    Customer find(String uuid);

}
