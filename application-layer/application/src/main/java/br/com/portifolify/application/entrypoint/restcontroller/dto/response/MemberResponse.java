package br.com.portifolify.application.entrypoint.restcontroller.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberResponse {

    private String id;

    private String name;

    private LocalDate dateOfBirth;

    private String cpf;

}