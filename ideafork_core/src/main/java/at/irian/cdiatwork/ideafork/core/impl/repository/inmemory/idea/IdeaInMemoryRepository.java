package at.irian.cdiatwork.ideafork.core.impl.repository.inmemory.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.idea.IdeaRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.core.impl.repository.inmemory.GenericInMemoryRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class IdeaInMemoryRepository extends GenericInMemoryRepository<Idea> implements IdeaRepository {
    private static final long serialVersionUID = -2577028101342086615L;

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
}
