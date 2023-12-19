package br.com.via.application.entrypoint.restcontroller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerPostRequest {

    @NotBlank
    private String document;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
