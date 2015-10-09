package at.irian.cdiatwork.ideafork.ee.backend.service;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;

import javax.inject.Inject;
import java.util.List;

@Service
public class SearchService {
    @Inject
    private IdeaManager ideaManager;

    public List<Idea> searchIdea(String searchText) {
        return ideaManager.search(searchText);
    }
}
