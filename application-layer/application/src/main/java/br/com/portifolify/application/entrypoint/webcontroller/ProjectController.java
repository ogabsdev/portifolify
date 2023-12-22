package br.com.portifolify.application.entrypoint.webcontroller;

import br.com.portifolify.application.entrypoint.webcontroller.dto.request.StoreProjectRequest;
import br.com.portifolify.core.usecase.FindAllManagerUseCase;
import br.com.portifolify.core.usecase.FindAllProjectRiskUseCase;
import br.com.portifolify.core.usecase.FindAllProjectStatusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("projects")
public class ProjectController {

    private final FindAllProjectRiskUseCase findAllProjectRiskUseCase;

    private final FindAllProjectStatusUseCase findAllProjectStatusUseCase;

    private final FindAllManagerUseCase findAllManagerUseCase;

    private static final String CREATE_VIEW_PATH = "project/create";

    private static final String VIEW_VAR_PROJECT_RISKS = "projectRisks";

    private static final String VIEW_VAR_PROJECT_STATUSES = "projectStatuses";

    private static final String VIEW_VAR_MANAGER = "managers";

    private static final String VIEW_VAR_PROJECT = "project";

    @GetMapping
    public ModelAndView index(ModelAndView modelAndView) {

        modelAndView.setViewName("project/index");

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
        modelAndView.setViewName(CREATE_VIEW_PATH);

        modelAndView.addObject(VIEW_VAR_PROJECT_RISKS, findAllProjectRiskUseCase.find());

        modelAndView.addObject(VIEW_VAR_PROJECT_STATUSES, findAllProjectStatusUseCase.find());

        modelAndView.addObject(VIEW_VAR_MANAGER, findAllManagerUseCase.find());

        modelAndView.addObject(VIEW_VAR_PROJECT, request);

        return modelAndView;
    }

}
