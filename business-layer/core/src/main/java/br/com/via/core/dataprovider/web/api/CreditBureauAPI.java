package br.com.via.core.dataprovider.web.api;

import br.com.via.core.dataprovider.web.api.dto.CreditScoreDTO;

public interface CreditBureauAPI {

    CreditScoreDTO findCreditScore(String document);

}
