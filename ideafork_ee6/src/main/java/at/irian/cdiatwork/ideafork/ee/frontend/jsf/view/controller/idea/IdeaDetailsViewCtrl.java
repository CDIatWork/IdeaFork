package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Stack;

@SessionScoped
@ViewController
public class IdeaDetailsViewCtrl implements Serializable {
    @Inject
    private IdeaManager ideaManager;

    private Idea currentIdea;

    private Stack<Idea> displayedIdeas = new Stack<Idea>();

    public String showIdea(Idea currentIdea) {
        this.currentIdea = currentIdea;
        return "/pages/idea/details.xhtml";
    }

    public void showOriginal() {
        displayedIdeas.push(currentIdea);
        currentIdea = ideaManager.loadById(currentIdea.getBaseIdeaId());
    }

    public String back() {
        if (displayedIdeas.empty()) {
            return "/pages/idea/list.xhtml";
        }
        currentIdea = displayedIdeas.pop();
        return null;
    }

    public Idea getCurrentIdea() {
        return currentIdea;
    }
}
