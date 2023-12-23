package br.com.portifolify.core.usecase.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {

    private String id;

    private String name;

    private LocalDate startDate;

    private LocalDate expectedEndDate;

    private LocalDate realEndDate;

    private String projectRisk;

    private String description;

    private Double budget;

    private String managerId;

    private String managerName;

    private String projectStatus;

}
