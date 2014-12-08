package at.irian.cdiatwork.ideafork.core.impl.repository.inmemory.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.repository.idea.IdeaRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.core.impl.repository.inmemory.GenericInMemoryRepository;

@Repository
public class IdeaInMemoryRepository extends GenericInMemoryRepository<Idea> implements IdeaRepository {
    private static final long serialVersionUID = -2577028101342086615L;
}
