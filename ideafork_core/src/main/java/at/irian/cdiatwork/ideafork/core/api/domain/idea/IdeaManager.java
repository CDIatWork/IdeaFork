package at.irian.cdiatwork.ideafork.core.api.domain.idea;

import at.irian.cdiatwork.ideafork.core.api.monitoring.Monitored;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.idea.IdeaRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@ApplicationScoped
@Typed(IdeaManager.class)
public class IdeaManager implements IdeaRepository {
    private static final long serialVersionUID = -2246005095175518718L;

    private IdeaValidator ideaValidator;
    private IdeaRepository ideaRepository;

    protected IdeaManager() {
        //needed by proxy-libs
    }

    @Inject
    protected IdeaManager(IdeaValidator ideaValidator, IdeaRepository ideaRepository) {
        this.ideaValidator = ideaValidator;
        this.ideaRepository = ideaRepository;
    }

    @Monitored(maxThreshold = 10)
    public Idea createIdeaFor(String topic, String category, User author) {
        Idea result = new Idea(topic, category, author);

        if (!this.ideaValidator.checkIdea(result)) {
             throw new IllegalArgumentException("Please try it harder next time!");
        }

        return result;
    }

    @Override
    public void save(Idea entity) {
        ideaRepository.save(entity);
    }

    @Override
    public void remove(Idea entity) {
        ideaRepository.remove(entity);
    }

    @Override
    public Idea loadById(String id) {
        return ideaRepository.loadById(id);
    }
}
