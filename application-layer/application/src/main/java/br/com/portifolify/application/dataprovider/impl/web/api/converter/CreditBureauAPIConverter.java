package br.com.portifolify.application.dataprovider.impl.web.api.converter;

import br.com.portifolify.application.dataprovider.impl.web.restclient.dto.response.CreditScoreResponse;
import br.com.portifolify.core.dataprovider.web.api.dto.CreditScoreDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CreditBureauAPIConverter {

    CreditScoreDTO convert(CreditScoreResponse creditScoreResponse);

}
