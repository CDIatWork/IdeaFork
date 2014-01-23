package at.irian.cdiatwork.ideafork.backend.api.domain.idea;

import javax.inject.Inject;

public class IdeaManager {
    private final IdeaValidator ideaValidator;

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
