package br.com.portifolify.core.usecase.dto.converter;

import br.com.portifolify.core.usecase.dto.CustomerDTO;
import br.com.portifolify.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CustomerUseCaseConverter {

    @Mappings({
            @Mapping(target = "scorePercentage", source = "creditScore.scorePercentage"),
            @Mapping(target = "scoreProcessingDate", source = "creditScore.processingDate")
    })
    CustomerDTO convert(Customer customer);

}
