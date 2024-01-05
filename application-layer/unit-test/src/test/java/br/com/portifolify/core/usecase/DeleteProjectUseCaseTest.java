package br.com.portifolify.core.usecase;

import br.com.portifolify.core.service.DeleteProjectService;
import br.com.portifolify.core.service.FindProjectByIdService;
import br.com.portifolify.core.usecase.exception.ProjectCanNotDeleteException;
import br.com.portifolify.core.usecase.exception.ProjectNotFoundException;
import br.com.portifolify.core.usecase.impl.DeleteProjectUseCaseImpl;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeleteProjectUseCaseTest {

    private final DeleteProjectService deleteProjectService = mock(DeleteProjectService.class);

    private final FindProjectByIdService findProjectByIdService = mock(FindProjectByIdService.class);

    private DeleteProjectUseCase deleteProjectUseCase;

    private final Manager manager = Manager.builder()
            .id(Id.builder().value("manager-id").build())
            .cpf(Cpf.builder().value("11111111111").build())
            .dateOfBirth(LocalDate.now().minusYears(28))
            .name("Manager")
            .build();

    private final Project.ProjectBuilder projectBuilder = Project.builder()
            .id(Id.builder().value("id-expected").build())
            .projectRisk(ProjectRisk.builder().description("baixo").build())
            .description("Description")
            .budget(125_000d)
            .startDate(LocalDate.now())
            .expectedEndDate(LocalDate.now().plusDays(2))
            .name("Project 01")
            .manager(manager);

    @BeforeEach
    void setup() {
        deleteProjectUseCase = new DeleteProjectUseCaseImpl(
                deleteProjectService,
                findProjectByIdService
        );
    }

    @Test
    void shouldThrowProjectNotFoundException() {

        when(findProjectByIdService.find(anyString())).thenReturn(null);

        String id = "id";

        assertThrows(ProjectNotFoundException.class, () -> deleteProjectUseCase.delete(id));
    }

    @Test
    void shouldThrowProjectCanNotDeleteException() {

        ProjectStatus projectStatus = ProjectStatus.builder()
                .description("Iniciado")
                .canDelete(false)
                .build();

        projectBuilder.projectStatus(projectStatus);

        when(findProjectByIdService.find(anyString())).thenReturn(projectBuilder.build());

        String id = "id";

        assertThrows(ProjectCanNotDeleteException.class, () -> deleteProjectUseCase.delete(id));
    }

    @Test
    void shouldDeleteProject() {

        ProjectStatus projectStatus = ProjectStatus.builder()
                .description("Em análise")
                .canDelete(true)
                .build();

        projectBuilder.projectStatus(projectStatus);

        when(findProjectByIdService.find(anyString())).thenReturn(projectBuilder.build());

        String id = "id";

        assertDoesNotThrow(() -> deleteProjectUseCase.delete(id));
    }

}
