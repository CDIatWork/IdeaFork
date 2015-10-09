package at.irian.cdiatwork.ideafork.ee.backend.service;

import at.irian.cdiatwork.ideafork.core.api.data.view.CategoryView;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.promotion.PromotionRequest;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.promotion.PromotionRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class IdeaService {
    @Inject
    private IdeaManager ideaManager;

    @Inject
    private PromotionRepository promotionRepository;

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

    public List<CategoryView> getHighestRatedCategories() {
        return this.ideaManager.getHighestRatedCategories();
    }

    public void requestIdeaPromotion(PromotionRequest promotionRequest) {
        this.promotionRepository.save(promotionRequest);
    }

    public List<Idea> loadRecentlyPromotedIdeas(User user) {
        List<Idea> originalIdeaList = this.promotionRepository.loadRecentlyPromotedIdeas(user);
        List<Idea> result = new ArrayList<Idea>(originalIdeaList.size());
        for (Idea idea : originalIdeaList) {
            result.add(this.ideaManager.forkIdea(idea, user));
        }
        return result;
    }

    public List<PromotionRequest> loadRecentIdeaPromotions(User currentUser, String searchHint) {
        if (searchHint == null) {
            searchHint = "*";
        }
        return this.promotionRepository.loadRecentIdeaPromotions(currentUser, searchHint);
    }

    public void promoteIdea(PromotionRequest selectedPromotionRequest) {
        this.promotionRepository.promoteIdea(selectedPromotionRequest);
    }
}