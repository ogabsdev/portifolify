package br.com.portifolify.application.dataprovider.impl.event.producer.converter;

import br.com.portifolify.application.dataprovider.impl.event.record.CustomerDomainEventRecord;
import br.com.portifolify.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CustomerDomainEventConverter {

    @Mapping(target = "scorePercentage", source = "creditScore.scorePercentage")
    @Mapping(target = "scoreProcessingDate", source = "creditScore.processingDate")
    CustomerDomainEventRecord convert(Customer customer);

}
