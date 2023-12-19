package br.com.portifolify.core.service.impl;

import br.com.portifolify.core.dataprovider.web.api.CreditBureauAPI;
import br.com.portifolify.core.dataprovider.web.api.dto.CreditScoreDTO;
import br.com.portifolify.core.service.CreditBureauService;
import br.com.portifolify.core.service.converter.CreditBureauServiceConverter;
import br.com.portifolify.domain.vo.CreditScore;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Named
@RequiredArgsConstructor
public class CreditBureauServiceImpl implements CreditBureauService {

    private final CreditBureauAPI creditBureauAPI;

    private final CreditBureauServiceConverter creditBureauServiceConverter;

    @Override
    public CreditScore findCreditScore(String document) {
        CreditScoreDTO creditScoreDTO = creditBureauAPI.findCreditScore(document);
        return creditBureauServiceConverter.convert(creditScoreDTO);
    }

}