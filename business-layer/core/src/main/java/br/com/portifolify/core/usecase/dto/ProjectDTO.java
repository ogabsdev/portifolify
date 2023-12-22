package br.com.portifolify.core.usecase.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProjectDTO {

    private String id;

    private String name;

    private LocalDateTime startDate;

    private LocalDateTime expectedEndDate;

    private LocalDateTime realEndDate;

    private String projectRiskId;

    private String projectRiskDescription;

    private String description;

    private Double budget;

    private String managerId;

    private String managerName;

    private String projectStatusId;

    private String projectStatusDescription;

}
