package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.promotion;

import at.irian.cdiatwork.ideafork.core.api.domain.promotion.PromotionRequest;
import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import org.apache.deltaspike.core.api.scope.ConversationGroup;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;
import org.apache.deltaspike.core.spi.scope.conversation.GroupedConversationManager;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@GroupedConversationScoped
@ConversationGroup(Pages.PromotionSelectionArea.class)
public class PromotionRequestSelectionViewCtrl implements Serializable {

    @Inject
    private GroupedConversationManager conversationManager;

    @Inject
    private IdeaService ideaService;

    private PromotionRequest selectedPromotionRequest;

    public Class<? extends Pages.PromotionSelectionArea> showPromotionRequest(PromotionRequest promotionRequest) {
        this.selectedPromotionRequest = promotionRequest;
        return Pages.PromotionSelectionArea.SelectPromotion.class;
    }

    public Class<? extends Pages> promote() {
        conversationManager.closeConversationGroup(Pages.PromotionSelectionArea.class);
        ideaService.promoteIdea(this.selectedPromotionRequest);
        return Pages.Index.class;
    }

    public PromotionRequest getSelectedPromotionRequest() {
        return selectedPromotionRequest;
    }
}
