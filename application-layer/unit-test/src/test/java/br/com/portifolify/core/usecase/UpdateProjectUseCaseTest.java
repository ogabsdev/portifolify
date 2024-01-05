package br.com.portifolify.core.usecase;

import br.com.portifolify.core.service.FindManagerService;
import br.com.portifolify.core.service.FindProjectByIdService;
import br.com.portifolify.core.service.FindProjectStatusByDescriptionService;
import br.com.portifolify.core.service.UpdateProjectService;
import br.com.portifolify.core.usecase.dto.ProjectDTO;
import br.com.portifolify.core.usecase.dto.converter.ProjectUseCaseConverter;
import br.com.portifolify.core.usecase.exception.ManagerNotFoundException;
import br.com.portifolify.core.usecase.exception.ProjectNotFoundException;
import br.com.portifolify.core.usecase.impl.UpdateProjectUseCaseImpl;
import br.com.portifolify.domain.Manager;
import br.com.portifolify.domain.Project;
import br.com.portifolify.domain.ProjectRisk;
import br.com.portifolify.domain.ProjectStatus;
import br.com.portifolify.domain.vo.Cpf;
import br.com.portifolify.domain.vo.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UpdateProjectUseCaseTest {

    private final FindManagerService findManagerService= mock(FindManagerService.class);

    private final UpdateProjectService updateProjectService = mock(UpdateProjectService.class);

    private final FindProjectByIdService findProjectByIdService = mock(FindProjectByIdService.class);

    private final ProjectUseCaseConverter projectUseCaseConverter = mock(ProjectUseCaseConverter.class);

    private final FindProjectStatusByDescriptionService findProjectStatusByDescriptionService = mock(
            FindProjectStatusByDescriptionService.class
    );

    private UpdateProjectUseCase updateProjectUseCase;

    @BeforeEach
    void setup() {
        updateProjectUseCase = new UpdateProjectUseCaseImpl(
                findManagerService,
                updateProjectService,
                findProjectByIdService,
                projectUseCaseConverter,
                findProjectStatusByDescriptionService
        );
    }

    @Test
    void shouldThrowProjectNotFoundException() {

        when(findProjectByIdService.find(anyString())).thenReturn(null);

        ProjectDTO dto = new ProjectDTO();

        assertThrows(ProjectNotFoundException.class, () -> updateProjectUseCase.update(dto));
    }

    @Test
    void shouldThrowManagerNotFoundException() {

        Manager manager = Manager.builder()
                .id(Id.builder().value("manager-id").build())
                .cpf(Cpf.builder().value("11111111111").build())
                .dateOfBirth(LocalDate.now().minusYears(28))
                .name("Manager")
                .build();

        ProjectStatus projectStatus = ProjectStatus.builder()
                .description("Em análise")
                .canDelete(true)
                .build();

        Project.ProjectBuilder projectBuilder = Project.builder()
                .id(Id.builder().value("id-expected").build())
                .projectStatus(projectStatus)
                .projectRisk(ProjectRisk.builder().description("baixo").build())
                .description("Description")
                .budget(125_000d)
                .startDate(LocalDate.now())
                .expectedEndDate(LocalDate.now().plusDays(2))
                .name("Project 01")
                .manager(manager);

        when(findProjectByIdService.find(anyString())).thenReturn(projectBuilder.build());

        when(findManagerService.find(anyString())).thenReturn(null);

        ProjectDTO dto = new ProjectDTO();
        dto.setId("id");
        dto.setManagerId("managerId");

        assertThrows(ManagerNotFoundException.class, () -> updateProjectUseCase.update(dto));
    }

    @Test
    void shouldUpdateProject() {
        ProjectDTO dtoArrange = new ProjectDTO();
        dtoArrange.setId("id");
        dtoArrange.setManagerId("ManagerId");
        dtoArrange.setProjectStatus("Em análise");

        Manager manager = Manager.builder()
                .id(Id.builder().value("manager-id").build())
                .cpf(Cpf.builder().value("11111111111").build())
                .dateOfBirth(LocalDate.now().minusYears(28))
                .name("Manager")
                .build();

        ProjectStatus projectStatus = ProjectStatus.builder()
                .description("Em análise")
                .canDelete(true)
                .build();

        Project.ProjectBuilder projectBuilder = Project.builder()
                .id(Id.builder().value("id-expected").build())
                .projectStatus(projectStatus)
                .projectRisk(ProjectRisk.builder().description("baixo").build())
                .description("Description")
                .budget(125_000d)
                .startDate(LocalDate.now())
                .expectedEndDate(LocalDate.now().plusDays(2))
                .name("Project 01")
                .manager(manager);

        Project project = projectBuilder.build();

        ProjectDTO dtoExpected = new ProjectDTO();
        dtoExpected.setId(project.getId().getValue());

        when(findProjectByIdService.find(anyString())).thenReturn(project);

        when(findManagerService.find(anyString())).thenReturn(manager);

        when(findProjectStatusByDescriptionService.find(anyString())).thenReturn(projectStatus);

        when(projectUseCaseConverter.convert(dtoArrange, projectStatus, manager)).thenReturn(projectBuilder);

        doNothing().when(updateProjectService).update(project);

        assertDoesNotThrow(() -> updateProjectUseCase.update(dtoArrange));
    }
}
