package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller;

import at.irian.cdiatwork.ideafork.core.api.data.view.CategoryView;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import org.apache.deltaspike.core.api.config.view.controller.PreRenderView;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@ViewController
public class IndexViewCtrl implements Serializable {
    @Inject
    private IdeaService ideaService;

    @Inject
    private ActiveUserHolder userHolder;

    private List<CategoryView> categories;
    private int categoryCount;

    private List<Idea> promotedIdeas;
    private int promotedIdeaCount;

    @PreRenderView
    public void onPreRenderView() {
        if (userHolder.isLoggedIn()) {
            categories = ideaService.getHighestRatedCategories();
            categoryCount = categories.size();
            promotedIdeas = ideaService.loadRecentlyPromotedIdeas(userHolder.getAuthenticatedUser());
            promotedIdeaCount = promotedIdeas.size();
        }
    }

    /*
     * generated
     */

    public int getCategoryCount() {
        return categoryCount;
    }

    public List<CategoryView> getCategories() {
        return categories;
    }

    public int getPromotedIdeaCount() {
        return promotedIdeaCount;
    }

    public List<Idea> getPromotedIdeas() {
        return promotedIdeas;
    }
}
