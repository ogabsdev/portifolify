package br.com.portifolify.application.entrypoint.webcontroller.dto.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class StoreProjectRequest {

    @NotBlank
    private String name;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate expectedEndDate;

    @Null
    private LocalDate realEndDate;

    @NotBlank
    private String projectRiskId;

    @NotBlank
    private String description;

    @NotNull
    @Min(1)
    private Double budget;

    @NotBlank
    private String managerId;

    @NotBlank
    private String projectStatusId;

    @NotEmpty
    private List<String> membersId;

}
