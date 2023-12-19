package br.com.portifolify.application.entrypoint.restcontroller.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponse {

    private String uuid;

    private String document;

    private String firstName;

    private String lastName;

    private Integer scorePercentage;

    private LocalDateTime scoreProcessingDate;

}
