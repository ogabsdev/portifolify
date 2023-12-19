package br.com.via.application.entrypoint.restcontroller.dto.converter;

import br.com.via.application.entrypoint.restcontroller.dto.request.CustomerPostRequest;
import br.com.via.application.entrypoint.restcontroller.dto.response.CustomerResponse;
import br.com.via.core.usecase.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CustomerControllerConverter {

    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "scorePercentage", ignore = true)
    @Mapping(target = "scoreProcessingDate", ignore = true)
    CustomerDTO convert(CustomerPostRequest customerRequest);

    CustomerResponse convert(CustomerDTO customer);

}
