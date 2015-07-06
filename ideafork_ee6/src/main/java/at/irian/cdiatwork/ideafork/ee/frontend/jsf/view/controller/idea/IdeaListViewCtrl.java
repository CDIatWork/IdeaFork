package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ViewController
public class IdeaListViewCtrl implements Serializable {
    @Inject
    private IdeaService ideaService;

    @Inject
    private ActiveUserHolder userHolder;

    private List<Idea> ideaList;

    public void onPreRenderView() {
        ideaList = ideaService.loadAllOfAuthor(userHolder.getAuthenticatedUser());
    }

    public void deleteIdea(Idea currentIdea) {
        this.ideaService.remove(currentIdea);
    }

    public List<Idea> getIdeaList() {
        return ideaList;
    }
}
