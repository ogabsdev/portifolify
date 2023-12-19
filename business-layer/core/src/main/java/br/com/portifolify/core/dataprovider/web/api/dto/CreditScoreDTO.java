package br.com.portifolify.core.dataprovider.web.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreditScoreDTO {

    private Integer scorePercentage;

    private LocalDateTime processingDate;

}