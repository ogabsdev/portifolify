package br.com.via.core.service.converter;

import br.com.via.core.dataprovider.web.api.dto.CreditScoreDTO;
import br.com.via.domain.vo.CreditScore;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface CreditBureauServiceConverter {

    CreditScore convert(CreditScoreDTO creditScoreDTO);

}
