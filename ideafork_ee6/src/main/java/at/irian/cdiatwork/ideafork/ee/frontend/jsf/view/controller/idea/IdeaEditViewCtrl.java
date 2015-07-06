package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;

import javax.inject.Inject;
import java.io.Serializable;

@ViewController
public class IdeaEditViewCtrl implements Serializable {
    @Inject
    private IdeaService ideaService;

    private Idea currentIdea;

    public Class<? extends Pages.Idea> editIdea(Idea currentIdea) {
        this.currentIdea = currentIdea;
        return Pages.Idea.Edit.class;
    }

    public Class<? extends Pages.Idea> save() {
        ideaService.save(currentIdea);
        return Pages.Idea.List.class;
    }

    public Idea getCurrentIdea() {
        return currentIdea;
    }
}
