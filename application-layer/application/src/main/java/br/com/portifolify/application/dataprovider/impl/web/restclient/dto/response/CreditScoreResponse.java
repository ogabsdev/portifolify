package br.com.portifolify.application.dataprovider.impl.web.restclient.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreditScoreResponse {

    private Integer scorePercentage;

    private LocalDateTime processingDate;

}
