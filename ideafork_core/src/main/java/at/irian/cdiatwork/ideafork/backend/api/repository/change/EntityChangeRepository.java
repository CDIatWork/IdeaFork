package at.irian.cdiatwork.ideafork.backend.api.repository.change;

import at.irian.cdiatwork.ideafork.backend.api.domain.change.EntityChange;
import at.irian.cdiatwork.ideafork.backend.api.repository.GenericRepository;

public interface EntityChangeRepository extends GenericRepository<EntityChange> {
    EntityChange findRevision(String entityId, long entityVersionToFind);
}
