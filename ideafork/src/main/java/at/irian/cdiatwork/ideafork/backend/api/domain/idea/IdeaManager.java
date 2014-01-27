package at.irian.cdiatwork.ideafork.backend.api.domain.idea;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class IdeaManager {
    private IdeaValidator ideaValidator;

    protected IdeaManager() {
        //needed by proxy-libs
    }

    @Inject
    protected IdeaManager(IdeaValidator ideaValidator) {
        this.ideaValidator = ideaValidator;
    }

    public Idea createIdeaFor(String topic, String category) {
        Idea result = new Idea(topic, category);

        if (!this.ideaValidator.checkIdea(result)) {
             throw new IllegalArgumentException("Please try it harder next time!");
        }

        return result;
    }
}
