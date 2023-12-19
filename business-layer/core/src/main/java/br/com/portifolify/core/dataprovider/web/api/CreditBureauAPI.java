package br.com.portifolify.core.dataprovider.web.api;

import br.com.portifolify.core.dataprovider.web.api.dto.CreditScoreDTO;

public interface CreditBureauAPI {

    CreditScoreDTO findCreditScore(String document);

}
