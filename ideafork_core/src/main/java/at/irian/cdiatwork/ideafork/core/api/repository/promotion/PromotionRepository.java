package at.irian.cdiatwork.ideafork.core.api.repository.promotion;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.promotion.PromotionRequest;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.GenericRepository;

import java.util.List;

public interface PromotionRepository extends GenericRepository<PromotionRequest> {

    List<PromotionRequest> loadRecentIdeaPromotions(User user, String searchHint);

    void promoteIdea(PromotionRequest promotionRequest);

    List<Idea> loadRecentlyPromotedIdeas(User user);
}