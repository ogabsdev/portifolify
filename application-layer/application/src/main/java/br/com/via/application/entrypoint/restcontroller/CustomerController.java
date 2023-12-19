package br.com.via.application.entrypoint.restcontroller;

import br.com.via.application.entrypoint.restcontroller.dto.converter.CustomerControllerConverter;
import br.com.via.application.entrypoint.restcontroller.dto.request.CustomerPostRequest;
import br.com.via.application.entrypoint.restcontroller.dto.response.CustomerResponse;
import br.com.via.core.usecase.CreateCustomerUseCase;
import br.com.via.core.usecase.CustomerInactivationUseCase;
import br.com.via.core.usecase.FindCustomerByUuidUseCase;
import br.com.via.core.usecase.dto.CustomerDTO;
import br.com.via.core.usecase.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerControllerConverter customerConverter;

    private final CreateCustomerUseCase createCustomerUseCase;

    private final FindCustomerByUuidUseCase findCustomerByUuidUseCase;

    private final CustomerInactivationUseCase customerInactivationUseCase;

    @PostMapping
    public ResponseEntity<Void> createCustomer(@RequestBody @Valid CustomerPostRequest customerRequest) {
        CustomerDTO customer = customerConverter.convert(customerRequest);
        CustomerDTO customerCreated = createCustomerUseCase.create(customer);
        return ResponseEntity.created(URI.create("/customers/" + customerCreated.getUuid())).build();
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable String uuid) {
        try {
            CustomerDTO customer = findCustomerByUuidUseCase.find(uuid);
            CustomerResponse customerResponse = customerConverter.convert(customer);
            return ResponseEntity.ok(customerResponse);
        } catch (CustomerNotFoundException e) {
            log.warn("Customer not found!", e);
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/{uuid}")
    public void deleteCustomer(@PathVariable String uuid) {
        customerInactivationUseCase.inactivate(uuid);
    }

}
