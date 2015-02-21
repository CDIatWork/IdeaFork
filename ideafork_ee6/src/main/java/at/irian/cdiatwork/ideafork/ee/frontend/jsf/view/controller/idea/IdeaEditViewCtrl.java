package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
@ViewController
public class IdeaEditViewCtrl implements Serializable {
    @Inject
    private IdeaService ideaService;

    private Idea currentIdea;

    public String editIdea(Idea currentIdea) {
        this.currentIdea = currentIdea;
        return "/pages/idea/edit.xhtml";
    }

    public String save() {
        ideaService.save(currentIdea);
        return "/pages/idea/list.xhtml";
    }

    public Idea getCurrentIdea() {
        return currentIdea;
    }
}
