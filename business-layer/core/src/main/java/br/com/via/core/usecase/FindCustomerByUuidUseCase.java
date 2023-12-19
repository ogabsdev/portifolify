package br.com.via.core.usecase;

import br.com.via.core.usecase.dto.CustomerDTO;

public interface FindCustomerByUuidUseCase {

    CustomerDTO find(String uuid);

}
