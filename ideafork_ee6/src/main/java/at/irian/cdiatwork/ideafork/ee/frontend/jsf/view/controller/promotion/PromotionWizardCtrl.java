package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.promotion;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.promotion.PromotionRequest;
import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.model.SelectableEntity;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import org.apache.deltaspike.core.api.config.view.controller.PreRenderView;
import org.apache.deltaspike.core.api.scope.GroupedConversation;
import org.apache.deltaspike.core.api.scope.GroupedConversationScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@GroupedConversationScoped
public class PromotionWizardCtrl implements Serializable {
    @Inject
    private IdeaService ideaService;

    @Inject
    private ActiveUserHolder userHolder;

    @Inject
    private GroupedConversation conversation;

    private List<SelectableEntity<Idea>> selectableIdeaList;
    private String rowClasses;

    private PromotionRequest promotionRequest;

    @PreRenderView
    public void onPreRenderView() {
        if (selectableIdeaList != null) {
            return;
        }

        List<Idea> loadedResult = ideaService.loadAllOfAuthor(userHolder.getAuthenticatedUser());

        selectableIdeaList = new ArrayList<SelectableEntity<Idea>>(loadedResult.size());
        for (Idea idea : loadedResult) {
            selectableIdeaList.add(new SelectableEntity<Idea>(idea));
        }
    }

    public void select(SelectableEntity<Idea> selectedIdea) {
        StringBuilder rowClassBuilder = new StringBuilder();
        for (SelectableEntity selectableIdea : selectableIdeaList) {
            if (selectableIdea == selectedIdea) { //check for the same instance
                selectedIdea.setSelected(true);
                promotionRequest = new PromotionRequest(selectedIdea.getEntity());
                rowClassBuilder.append("selectedPromotionTableRow, ");
            } else {
                selectableIdea.setSelected(false);
                rowClassBuilder.append("'', ");
            }
        }
        rowClasses = rowClassBuilder.toString();
        rowClasses = rowClasses.substring(0, rowClasses.lastIndexOf(","));
    }

    public Class<? extends Pages.PromotionWizard> toStep2() {
        return Pages.PromotionWizard.Step2.class;
    }

    public Class<? extends Pages.PromotionWizard> showConfirmation() {
        return Pages.PromotionWizard.FinalStep.class;
    }

    public Class<? extends Pages> savePromotionRequest() {
        this.ideaService.requestIdeaPromotion(this.promotionRequest);
        this.conversation.close();
        return Pages.Index.class;
    }

    /*
     * generated
     */

    public List<SelectableEntity<Idea>> getSelectableIdeaList() {
        return selectableIdeaList;
    }

    public String getRowClasses() {
        return rowClasses;
    }

    public PromotionRequest getPromotionRequest() {
        return promotionRequest;
    }
}
