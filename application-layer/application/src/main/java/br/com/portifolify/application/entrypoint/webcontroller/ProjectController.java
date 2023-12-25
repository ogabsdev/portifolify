package br.com.portifolify.application.entrypoint.webcontroller;

import br.com.portifolify.application.entrypoint.webcontroller.dto.converter.ProjectWebControllerConverter;
import br.com.portifolify.application.entrypoint.webcontroller.dto.request.StoreProjectRequest;
import br.com.portifolify.application.entrypoint.webcontroller.dto.request.UpdateProjectRequest;
import br.com.portifolify.core.usecase.*;
import br.com.portifolify.core.usecase.dto.ProjectDTO;
import br.com.portifolify.core.usecase.exception.ManagerNotFoundException;
import br.com.portifolify.core.usecase.exception.ProjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    private final FindProjectUseCase findProjectUseCase;

    private final CreateProjectUseCase createProjectUseCase;

    private final UpdateProjectUseCase updateProjectUseCase;

    private final FindAllManagerUseCase findAllManagerUseCase;

    private final FindProjectByIdUseCase findProjectByIdUseCase;

    private final FindAllProjectRiskUseCase findAllProjectRiskUseCase;

    private final FindAllProjectStatusUseCase findAllProjectStatusUseCase;

    private final ProjectWebControllerConverter projectControllerConverter;

    private static final String VIEW_VAR_MANAGER = "managers";

    private static final String VIEW_VAR_PROJECT = "project";

    private static final String VIEW_VAR_PROJECTS = "projects";

    private static final String EDIT_VIEW_PATH = "project/edit";

    private static final String INDEX_VIEW_PATH = "project/index";

    private static final String CREATE_VIEW_PATH = "project/create";

    private static final String VIEW_VAR_PROJECT_RISKS = "projectRisks";

    private static final String VIEW_VAR_PROJECT_STATUSES = "projectStatuses";

    private static final ModelAndView REDIRECT_AFTER_ACTION = new ModelAndView("redirect:/projects");

    @GetMapping
    public ModelAndView index(
            ModelAndView modelAndView,
            @RequestParam(name = "q", defaultValue = "") String query
    ) {

        modelAndView.setViewName(INDEX_VIEW_PATH);

        modelAndView.addObject(VIEW_VAR_PROJECTS, findProjectUseCase.find(query));

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create(ModelAndView modelAndView) {

        modelAndView.setViewName(CREATE_VIEW_PATH);

        modelAndView.addObject(VIEW_VAR_PROJECT_RISKS, findAllProjectRiskUseCase.find());

        modelAndView.addObject(VIEW_VAR_PROJECT_STATUSES, findAllProjectStatusUseCase.find());

        modelAndView.addObject(VIEW_VAR_MANAGER, findAllManagerUseCase.find());

        modelAndView.addObject(VIEW_VAR_PROJECT, new StoreProjectRequest());

        return modelAndView;
    }

    @PostMapping
    public ModelAndView store(
            ModelAndView modelAndView,
            @Valid @ModelAttribute(VIEW_VAR_PROJECT) StoreProjectRequest request,
            BindingResult validationResult
    ) {

        try {
            ProjectDTO projectDTO = projectControllerConverter.convert(request);

            createProjectUseCase.create(projectDTO);

            return REDIRECT_AFTER_ACTION;
        } catch (ManagerNotFoundException e) {
            log.warn("Manager not found", e);

            validationResult.rejectValue("managerId", "Gerente não é válido");
        }

        modelAndView.setViewName(CREATE_VIEW_PATH);

        modelAndView.addObject(VIEW_VAR_PROJECT_RISKS, findAllProjectRiskUseCase.find());

        modelAndView.addObject(VIEW_VAR_PROJECT_STATUSES, findAllProjectStatusUseCase.find());

        modelAndView.addObject(VIEW_VAR_MANAGER, findAllManagerUseCase.find());

        modelAndView.addObject(VIEW_VAR_PROJECT, request);

        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable("id") String id) {
        try {
            ProjectDTO projectDTO = findProjectByIdUseCase.find(id);

            modelAndView.addObject(VIEW_VAR_PROJECT, projectControllerConverter.convert(projectDTO));

            modelAndView.setViewName(EDIT_VIEW_PATH);

            modelAndView.addObject(VIEW_VAR_PROJECT_RISKS, findAllProjectRiskUseCase.find());

            modelAndView.addObject(VIEW_VAR_PROJECT_STATUSES, findAllProjectStatusUseCase.find());

            modelAndView.addObject(VIEW_VAR_MANAGER, findAllManagerUseCase.find());

            return modelAndView;

        } catch (ProjectNotFoundException e) {
            log.warn("Project not found", e);

            return REDIRECT_AFTER_ACTION;
        }
    }

    @PostMapping("/{id}")
    public ModelAndView update(
            ModelAndView modelAndView,
            @PathVariable("id") String id,
            @Valid @ModelAttribute(VIEW_VAR_PROJECT) UpdateProjectRequest request,
            BindingResult validationResult
    ) {
        try {
            request.setId(id);

            updateProjectUseCase.update(projectControllerConverter.convert(request));

            modelAndView.addObject(VIEW_VAR_PROJECT, request);

            modelAndView.setViewName(EDIT_VIEW_PATH);

            modelAndView.addObject(VIEW_VAR_PROJECT_RISKS, findAllProjectRiskUseCase.find());

            modelAndView.addObject(VIEW_VAR_PROJECT_STATUSES, findAllProjectStatusUseCase.find());

            modelAndView.addObject(VIEW_VAR_MANAGER, findAllManagerUseCase.find());

        } catch (ProjectNotFoundException e) {
            log.warn("Project not found", e);

            validationResult.rejectValue("name", "Projeto não existe em nossos registros");
        }

        return modelAndView;
    }
}
