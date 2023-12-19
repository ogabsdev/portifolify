package br.com.via.application.dataprovider.impl.web.api;

import br.com.via.application.dataprovider.impl.web.restclient.dto.response.CreditScoreResponse;
import br.com.via.core.dataprovider.web.api.CreditBureauAPI;
import br.com.via.core.dataprovider.web.api.dto.CreditScoreDTO;
import br.com.via.application.dataprovider.impl.web.api.converter.CreditBureauAPIConverter;
import br.com.via.application.dataprovider.impl.web.restclient.CreditBureauFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditBureauAPIImpl implements CreditBureauAPI {

    private final CreditBureauFeignClient creditBureauFeignClient;

    private final CreditBureauAPIConverter creditBureauAPIConverter;

    @Override
    public CreditScoreDTO findCreditScore(String document) {
        CreditScoreResponse creditScoreResponse = creditBureauFeignClient.getCreditScore(document);
        return creditBureauAPIConverter.convert(creditScoreResponse);
    }

}
