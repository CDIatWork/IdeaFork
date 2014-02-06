package at.irian.cdiatwork.ideafork.backend.impl.repository.inmemory.idea;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.monitoring.Monitored;
import at.irian.cdiatwork.ideafork.backend.api.repository.idea.IdeaRepository;
import at.irian.cdiatwork.ideafork.backend.impl.repository.inmemory.GenericInMemoryRepository;

@Monitored
public class IdeaInMemoryRepository extends GenericInMemoryRepository<Idea> implements IdeaRepository {
}
