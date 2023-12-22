package br.com.portifolify.core.usecase.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagerDTO {

    private String id;

    private String name;

    private LocalDate dateOfBirth;

    private String cpf;

}
