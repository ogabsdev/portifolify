package br.com.portifolify.application.dataprovider.impl.persistence.entity.converter;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.CustomerEntity;
import br.com.portifolify.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CustomerEntityConverter {

    @Mappings({
            @Mapping(target = "scorePercentage", source = "creditScore.scorePercentage"),
            @Mapping(target = "scoreProcessingDate", source = "creditScore.processingDate"),
            @Mapping(target = "version", ignore = true)
    })
    CustomerEntity convert(Customer customer);

    @Mappings({
            @Mapping(target = "scorePercentage", source = "customer.creditScore.scorePercentage"),
            @Mapping(target = "scoreProcessingDate", source = "customer.creditScore.processingDate"),
            @Mapping(target = "version", source = "version")
    })
    CustomerEntity convert(Customer customer, Long version);

    @Mappings({
            @Mapping(target = "creditScore.scorePercentage", source = "scorePercentage"),
            @Mapping(target = "creditScore.processingDate", source = "scoreProcessingDate")
    })
    Customer convert(CustomerEntity customerEntity);

}
