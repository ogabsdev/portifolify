package br.com.portifolify.domain.vo;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class CreditScore {

    @NonNull
    Integer scorePercentage;

    @NonNull
    LocalDateTime processingDate;

}
