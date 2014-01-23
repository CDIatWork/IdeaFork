package at.irian.cdiatwork.ideafork.backend.api.domain.idea;

//hint for later: will be replaced with a bean-validation constraint
public class IdeaValidator {
    public boolean checkIdea(Idea idea) {
        return idea.getCategory() != null && idea.getTopic() != null;
    }
}
