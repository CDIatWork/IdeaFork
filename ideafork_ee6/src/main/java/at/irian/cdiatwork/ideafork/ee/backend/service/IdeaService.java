package at.irian.cdiatwork.ideafork.ee.backend.service;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class IdeaService {
    @Inject
    private IdeaManager ideaManager;

    public Idea createIdeaFor(String topic, String category, User author) {
        return this.ideaManager.createIdeaFor(topic, category, author);
    }

    public void save(Idea idea) {
        this.ideaManager.save(idea);
    }

    public List<Idea> loadAllOfAuthor(User author) {
        return this.ideaManager.loadAllOfAuthor(author);
    }

    public void remove(Idea idea) {
        this.ideaManager.remove(idea);
    }
}