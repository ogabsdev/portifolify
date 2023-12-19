package br.com.via.core.usecase.impl;

import br.com.via.core.service.FindCustomerService;
import br.com.via.core.usecase.FindCustomerByUuidUseCase;
import br.com.via.core.usecase.dto.CustomerDTO;
import br.com.via.core.usecase.dto.converter.CustomerUseCaseConverter;
import br.com.via.core.usecase.exception.CustomerNotFoundException;
import br.com.via.domain.Customer;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@Transactional
@RequiredArgsConstructor
public class FindCustomerByUuidUseCaseImpl implements FindCustomerByUuidUseCase {

    private final FindCustomerService findCustomerService;

    private final CustomerUseCaseConverter customerUseCaseConverter;

    @Override
    public CustomerDTO find(String uuid) {
        Customer customer = findCustomerService.find(uuid);

        if (customer == null) {
            throw new CustomerNotFoundException(String.format("Customer %s not found.", uuid));
        }

        return customerUseCaseConverter.convert(customer);
    }

}
