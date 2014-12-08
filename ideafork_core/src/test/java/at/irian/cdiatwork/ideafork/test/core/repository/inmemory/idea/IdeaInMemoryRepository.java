package at.irian.cdiatwork.ideafork.test.core.repository.inmemory.idea;

import at.irian.cdiatwork.ideafork.core.api.config.ApplicationConfig;
import at.irian.cdiatwork.ideafork.core.api.data.view.CategoryView;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.idea.IdeaRepository;
import at.irian.cdiatwork.ideafork.test.core.repository.inmemory.GenericInMemoryRepository;
import at.irian.cdiatwork.ideafork.test.core.repository.inmemory.MockedRepository;

import javax.inject.Inject;
import java.util.*;

@MockedRepository
public class IdeaInMemoryRepository extends GenericInMemoryRepository<Idea> implements IdeaRepository {
    private static final long serialVersionUID = -2577028101342086615L;

    private int maxNumberOfHighestRatedCategories;

    protected IdeaInMemoryRepository() {
    }

    @Inject
    protected IdeaInMemoryRepository(ApplicationConfig config) {
        this.maxNumberOfHighestRatedCategories = config.getMaxNumberOfHighestRatedCategories();
    }

    @Override
    public List<Idea> loadAllOfAuthor(User author) {
        List<Idea> result = new ArrayList<Idea>();

        for(Idea idea : this.entityMap.values()) {
            if (author.equals(idea.getAuthor())) {
                result.add(clone(idea));
            }
        }
        return result;
    }

    @Override
    public List<Idea> search(String searchText) {
        List<Idea> result = new ArrayList<Idea>();

        for (Idea idea : loadAll()) {
            if (idea.getTopic().contains(searchText) || idea.getDescription().contains(searchText)) {
                result.add(idea);
            }
        }
        return result;
    }

    @Override
    public List<CategoryView> getHighestRatedCategories() {
        Map<String, CategoryView> categoryViews = new HashMap<String, CategoryView>();
        List<Idea> allIdeas = loadAll();

        int i = 0;
        CategoryView categoryView;
        for (Idea currentIdea : allIdeas) {
            if (++i > maxNumberOfHighestRatedCategories) {
                break;
            }

            categoryView = categoryViews.get(currentIdea.getCategory());

            if (categoryView == null) {
                categoryView = new CategoryView(currentIdea.getCategory());
                categoryViews.put(currentIdea.getCategory(), categoryView);
            } else {
                categoryView.increaseCount();
            }
        }

        List<CategoryView> result = new ArrayList<CategoryView>(categoryViews.values());
        Collections.sort(result, new Comparator<CategoryView>() {
            @Override
            public int compare(CategoryView o1, CategoryView o2) {
                return o1.getCount() <= o2.getCount() ? 1 : -1;
            }
        });
        return result;
    }
}
