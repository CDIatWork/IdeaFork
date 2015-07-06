package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@SessionScoped
@ViewController
public class IdeaForkViewCtrl implements Serializable {
    @Inject
    private IdeaEditViewCtrl ideaEditViewCtrl;

    @Inject
    private IdeaManager ideaManager;

    @Inject
    private ActiveUserHolder userHolder;

    public Class<? extends Pages.Idea> forkIdea(Idea currentIdea) {
        Idea forkedIdea = ideaManager.forkIdea(currentIdea, userHolder.getAuthenticatedUser());
        ideaEditViewCtrl.editIdea(forkedIdea);
        return Pages.Idea.Edit.class;
    }
}
