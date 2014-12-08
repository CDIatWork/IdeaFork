package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;

@Stateful //possible as well
@ViewController
//can be optimized via @TransactionAttribute and @Lock
public class IdeaCreateViewCtrl implements Serializable {
    @Inject
    private IdeaManager ideaManager;

    @Inject
    private ActiveUserHolder userHolder;

    private String topic;
    private String category;
    private String description;

    public String save() {
        Idea ideaToSave = ideaManager.createIdeaFor(topic, category, userHolder.getAuthenticatedUser());
        ideaToSave.setDescription(description);
        ideaManager.save(ideaToSave);
        return "/pages/idea/overview.xhtml";
    }

    /*
     * generated
     */

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
