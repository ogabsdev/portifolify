package br.com.portifolify.domain.vo;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Cpf {

    @NonNull
    String value;

}