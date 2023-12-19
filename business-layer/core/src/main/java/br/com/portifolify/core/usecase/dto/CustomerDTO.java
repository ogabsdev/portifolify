package br.com.portifolify.core.usecase.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerDTO {

    private String uuid;

    private String document;

    private String firstName;

    private String lastName;

    private Integer scorePercentage;

    private LocalDateTime scoreProcessingDate;

}
