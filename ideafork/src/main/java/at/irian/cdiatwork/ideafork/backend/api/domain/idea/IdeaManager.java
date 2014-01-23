package at.irian.cdiatwork.ideafork.backend.api.domain.idea;

public class IdeaManager {
    public Idea createIdeaFor(String topic, String category) {
        return new Idea(topic, category);
    }
}
