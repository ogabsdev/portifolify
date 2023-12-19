package br.com.portifolify.core.usecase;

import br.com.portifolify.core.usecase.dto.CustomerDTO;

public interface CreateCustomerUseCase {

    CustomerDTO create(CustomerDTO customer);

}
