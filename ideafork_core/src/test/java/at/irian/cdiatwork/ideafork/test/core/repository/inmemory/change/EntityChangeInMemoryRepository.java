package at.irian.cdiatwork.ideafork.test.core.repository.inmemory.change;

import at.irian.cdiatwork.ideafork.core.api.domain.change.EntityChange;
import at.irian.cdiatwork.ideafork.core.api.repository.change.EntityChangeRepository;
import at.irian.cdiatwork.ideafork.test.core.repository.inmemory.GenericInMemoryRepository;
import at.irian.cdiatwork.ideafork.test.core.repository.inmemory.MockedRepository;

@MockedRepository
public class EntityChangeInMemoryRepository extends GenericInMemoryRepository<EntityChange> implements EntityChangeRepository {
    private static final long serialVersionUID = 4943122589188849100L;

    @Override
    public EntityChange findRevision(String entityId, long entityVersionToFind) {
        for (EntityChange current : entityMap.values()) {
            if (current.getEntityId().equals(entityId) &&
                    current.getEntityVersion() == entityVersionToFind) {
                return current;
            }
        }
        return null;
    }
}
