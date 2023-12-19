package br.com.via.application.dataprovider.impl.event.producer.converter;

import br.com.via.application.dataprovider.impl.event.record.CustomerDomainEventRecord;
import br.com.via.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CustomerDomainEventConverter {

    @Mappings({
            @Mapping(target = "scorePercentage", source = "creditScore.scorePercentage"),
            @Mapping(target = "scoreProcessingDate", source = "creditScore.processingDate")
    })
    CustomerDomainEventRecord convert(Customer customer);

}
