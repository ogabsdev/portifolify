package br.com.portifolify.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessReason {

    CUSTOMER_REGISTER("customer-register"),
    CUSTOMER_INACTIVATION("customer-inactivation");

    private final String value;

}
