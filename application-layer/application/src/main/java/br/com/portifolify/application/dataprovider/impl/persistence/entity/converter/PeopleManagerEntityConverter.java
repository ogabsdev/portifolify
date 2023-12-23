package br.com.portifolify.application.dataprovider.impl.persistence.entity.converter;

import br.com.portifolify.application.dataprovider.impl.persistence.entity.PeopleEntity;
import br.com.portifolify.domain.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JSR330)
public interface PeopleManagerEntityConverter {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "cpf", source = "cpf.value")
    PeopleEntity convert(Manager manager);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "cpf.value", source = "cpf")
    Manager convert(PeopleEntity peopleEntity);


    @Mapping(target = "id.value", source = "encryptedId")
    @Mapping(target = "name", source = "peopleEntity.name")
    @Mapping(target = "dateOfBirth", source = "peopleEntity.dateOfBirth")
    @Mapping(target = "cpf.value", source = "peopleEntity.cpf")
    Manager convert(String encryptedId, PeopleEntity peopleEntity);

}
