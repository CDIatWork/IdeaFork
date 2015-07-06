package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.promotion;

import at.irian.cdiatwork.ideafork.core.api.domain.promotion.PromotionRequest;
import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import org.apache.deltaspike.core.api.config.view.controller.PreRenderView;
import org.apache.deltaspike.core.api.config.view.navigation.NavigationParameterContext;
import org.apache.deltaspike.core.api.scope.ConversationGroup;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@GroupedConversationScoped
@ConversationGroup(Pages.PromotionSelectionArea.class)
public class PromotionRequestListViewCtrl implements Serializable {

    @Inject
    private IdeaService ideaService;

    @Inject
    private ActiveUserHolder userHolder;

    @Inject
    private NavigationParameterContext navigationParameterContext;

    private String searchHint;

    private List<PromotionRequest> foundPromotionRequests;

    @PreRenderView
    public void onPreRenderView() {
        foundPromotionRequests = ideaService.loadRecentIdeaPromotions(userHolder.getAuthenticatedUser(), searchHint);
    }

    public Class<? extends Pages.PromotionSelectionArea> applyFilter() {
        navigationParameterContext.addPageParameter("searchHint", searchHint);
        return Pages.PromotionSelectionArea.ListPromotions.class;
    }

    /*
     * generated
     */

    public List<PromotionRequest> getFoundPromotionRequests() {
        return foundPromotionRequests;
    }

    public String getSearchHint() {
        return searchHint;
    }

    public void setSearchHint(String searchHint) {
        this.searchHint = searchHint;
    }
}
