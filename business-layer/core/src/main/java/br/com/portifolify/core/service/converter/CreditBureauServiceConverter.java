package br.com.portifolify.core.service.converter;

import br.com.portifolify.core.dataprovider.web.api.dto.CreditScoreDTO;
import br.com.portifolify.domain.vo.CreditScore;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CreditBureauServiceConverter {

    CreditScore convert(CreditScoreDTO creditScoreDTO);

}
