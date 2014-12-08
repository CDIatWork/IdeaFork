package at.irian.cdiatwork.ideafork.core.api.repository.change;

import at.irian.cdiatwork.ideafork.core.api.domain.change.EntityChange;
import at.irian.cdiatwork.ideafork.core.api.repository.GenericRepository;

public interface EntityChangeRepository extends GenericRepository<EntityChange> {
    EntityChange findRevision(String entityId, long entityVersionToFind);
}
