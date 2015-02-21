package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.idea;

import at.irian.cdiatwork.ideafork.ee.backend.service.IdeaService;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.inject.Inject;
import java.io.Serializable;

@ViewController
public class IdeaCreateViewCtrl implements Serializable {

    @Inject
    private IdeaService ideaService;

    @Inject
    private ActiveUserHolder userHolder;

    private String topic;
    private String category;
    private String description;

    public String save() {
        Idea ideaToSave = ideaService.createIdeaFor(topic, category, userHolder.getAuthenticatedUser());
        ideaToSave.setDescription(description);
        ideaService.save(ideaToSave);
        return "/pages/idea/overview.xhtml";
    }

    public String createWith(String category) {
        this.category = category;
        return "/pages/idea/create.xhtml";
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
