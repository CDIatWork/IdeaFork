package at.irian.cdiatwork.ideafork.core.api.domain.idea;

//hint for later: will be replaced with a bean-validation constraint
public class IdeaValidator {
    public boolean checkIdea(Idea idea) {
        return idea.getCategory() != null && idea.getTopic() != null && idea.getAuthor() != null;
    }
}
