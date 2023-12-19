package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.CustomerInactivationService;
import br.com.portifolify.core.service.FindCustomerService;
import br.com.portifolify.core.usecase.CustomerInactivationUseCase;
import br.com.portifolify.core.usecase.exception.CustomerNotFoundException;
import br.com.portifolify.domain.Customer;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@Transactional
@RequiredArgsConstructor
public class CustomerInactivationUseCaseImpl implements CustomerInactivationUseCase {

    private final FindCustomerService findCustomerService;

    private final CustomerInactivationService customerInactivationService;

    @Override
    public void inactivate(String uuid) {
        Customer customer = findCustomerService.find(uuid);

        if (customer == null) {
            throw new CustomerNotFoundException(String.format("Customer %s not found.", uuid));
        }

        customerInactivationService.inactivate(customer);
    }

}
