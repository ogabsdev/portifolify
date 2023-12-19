package br.com.via.core.service;

import br.com.via.domain.vo.CreditScore;

public interface CreditBureauService {

    CreditScore findCreditScore(String document);

}
