package br.com.portifolify.core.service;

import br.com.portifolify.domain.vo.CreditScore;

public interface CreditBureauService {

    CreditScore findCreditScore(String document);

}
