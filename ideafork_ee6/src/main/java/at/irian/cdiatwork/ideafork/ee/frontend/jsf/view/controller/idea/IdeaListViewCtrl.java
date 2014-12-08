package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateful
@SessionScoped
@ViewController
//can be optimized via @TransactionAttribute and @Lock
public class IdeaListViewCtrl implements Serializable {
    @Inject
    private IdeaManager ideaManager;

    @Inject
    private ActiveUserHolder userHolder;

    private List<Idea> ideaList;

    public void onPreRenderView() {
        ideaList = ideaManager.loadAllOfAuthor(userHolder.getAuthenticatedUser());
    }

    public void deleteIdea(Idea currentIdea) {
        this.ideaManager.remove(currentIdea);
    }

    public List<Idea> getIdeaList() {
        return ideaList;
    }
}
