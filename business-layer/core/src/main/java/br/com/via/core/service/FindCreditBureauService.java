package br.com.via.core.service;

import br.com.via.domain.vo.CreditScore;

public interface FindCreditBureauService {

    CreditScore findCreditScore(String document);

}
