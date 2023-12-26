package br.com.portifolify.core.usecase;

import br.com.portifolify.core.service.CreateProjectService;
import br.com.portifolify.core.service.FindManagerService;
import br.com.portifolify.core.service.FindProjectStatusByDescriptionService;
import br.com.portifolify.core.usecase.dto.ProjectDTO;
import br.com.portifolify.core.usecase.dto.converter.ProjectUseCaseConverter;
import br.com.portifolify.core.usecase.exception.ManagerNotFoundException;
import br.com.portifolify.core.usecase.impl.CreateProjectUseCaseImpl;
import br.com.portifolify.domain.Manager;
import br.com.portifolify.domain.Project;
import br.com.portifolify.domain.ProjectRisk;
import br.com.portifolify.domain.ProjectStatus;
import br.com.portifolify.domain.vo.Cpf;
import br.com.portifolify.domain.vo.Id;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateProjectUseCaseTest {

    private final FindManagerService findManagerService= mock(FindManagerService.class);

    private final CreateProjectService createProjectService = mock(CreateProjectService.class);

    private final ProjectUseCaseConverter projectUseCaseConverter = mock(ProjectUseCaseConverter.class);

    private final FindProjectStatusByDescriptionService findProjectStatusByDescriptionService = mock(
            FindProjectStatusByDescriptionService.class
    );

    private CreateProjectUseCase createProjectUseCase;

    @BeforeEach
    void setup() {
        createProjectUseCase = new CreateProjectUseCaseImpl(
                findManagerService,
                createProjectService,
                projectUseCaseConverter,
                findProjectStatusByDescriptionService
        );
    }

    @Test
    void shouldThrowManagerNotFoundException() {

        when(findManagerService.find(anyString())).thenReturn(null);

        ProjectDTO dto = new ProjectDTO();

        assertThrows(ManagerNotFoundException.class, () -> createProjectUseCase.create(dto));
    }

    @Test
    void shouldCreateProject() {
        ProjectDTO dtoArrange = new ProjectDTO();
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

        when(findManagerService.find(anyString())).thenReturn(manager);

        when(findProjectStatusByDescriptionService.find(projectStatus.getDescription())).thenReturn(projectStatus);

        when(projectUseCaseConverter.convert(dtoArrange, projectStatus, manager)).thenReturn(projectBuilder);

        when(createProjectService.create(projectBuilder)).thenReturn(project);

        when(projectUseCaseConverter.convert(project)).thenReturn(dtoExpected);

        ProjectDTO dtoReturned = createProjectUseCase.create(dtoArrange);

        assertEquals(dtoExpected.getId(), dtoReturned.getId());
    }
}
