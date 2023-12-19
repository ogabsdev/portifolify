package br.com.via.core.service.impl;

import br.com.via.core.dataprovider.web.api.CreditBureauAPI;
import br.com.via.core.dataprovider.web.api.dto.CreditScoreDTO;
import br.com.via.core.service.FindCreditBureauService;
import br.com.via.core.service.converter.CreditBureauServiceConverter;
import br.com.via.domain.vo.CreditScore;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequiredArgsConstructor
public class FindCreditBureauServiceImpl implements FindCreditBureauService {

    private final CreditBureauAPI creditBureauAPI;

    private final CreditBureauServiceConverter creditBureauServiceConverter;

    @Override
    public CreditScore findCreditScore(String document) {
        CreditScoreDTO creditScoreDTO = creditBureauAPI.findCreditScore(document);
        return creditBureauServiceConverter.convert(creditScoreDTO);
    }

}
