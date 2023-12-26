package br.com.portifolify.core.usecase.impl;

import br.com.portifolify.core.service.DeleteProjectService;
import br.com.portifolify.core.service.FindProjectByIdService;
import br.com.portifolify.core.usecase.DeleteProjectUseCase;
import br.com.portifolify.core.usecase.exception.ProjectCanNotDeleteException;
import br.com.portifolify.core.usecase.exception.ProjectNotFoundException;
import br.com.portifolify.domain.Project;
import lombok.RequiredArgsConstructor;

import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Objects;

@Named
@Transactional
@RequiredArgsConstructor
public class DeleteProjectUseCaseImpl implements DeleteProjectUseCase {

    private final DeleteProjectService deleteProjectService;

    private final FindProjectByIdService findProjectByIdService;

    @Override
    public void delete(String id) {

        Project project = findProjectByIdService.find(id);

        if (Objects.isNull(project)) {
            throw new ProjectNotFoundException(String.format("Project %s not found", id));
        }

        if (! project.getProjectStatus().isCanDelete()) {
            throw new ProjectCanNotDeleteException(String.format("Project %s not can deleted", id));
        }

        deleteProjectService.delete(project);
    }

}
