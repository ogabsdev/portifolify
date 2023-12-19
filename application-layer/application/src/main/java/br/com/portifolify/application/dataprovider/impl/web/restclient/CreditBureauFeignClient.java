package br.com.portifolify.application.dataprovider.impl.web.restclient;

import br.com.portifolify.application.dataprovider.impl.web.restclient.dto.response.CreditScoreResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "credit-bureau-client", url = "${external-service.credit-bureau-api.url}")
public interface CreditBureauFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/documents/{document}/credit-score")
    CreditScoreResponse getCreditScore(@PathVariable String document);

}
