package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@Stateful
@SessionScoped
@ViewController
//can be optimized via @TransactionAttribute and @Lock
public class IdeaEditViewCtrl implements Serializable {
    @Inject
    private IdeaManager ideaManager;

    private Idea currentIdea;

    public String editIdea(Idea currentIdea) {
        this.currentIdea = currentIdea;
        return "/pages/idea/edit.xhtml";
    }

    public String save() {
        ideaManager.save(currentIdea);
        return "/pages/idea/list.xhtml";
    }

    public Idea getCurrentIdea() {
        return currentIdea;
    }
}
