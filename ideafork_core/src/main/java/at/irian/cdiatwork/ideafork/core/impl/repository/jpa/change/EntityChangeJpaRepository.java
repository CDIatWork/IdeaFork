package at.irian.cdiatwork.ideafork.core.impl.repository.jpa.change;

import at.irian.cdiatwork.ideafork.core.api.domain.change.EntityChange;
import at.irian.cdiatwork.ideafork.core.api.repository.change.EntityChangeRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.core.impl.repository.jpa.GenericJpaRepository;

import javax.persistence.NoResultException;

@Repository
public class EntityChangeJpaRepository extends GenericJpaRepository<EntityChange> implements EntityChangeRepository {
    private static final long serialVersionUID = 4943122589188849100L;

    @Override
    public EntityChange findRevision(String entityId, long entityVersionToFind) {
        try {
            return entityManager.createQuery(
                    "select ec from EntityChange ec where ec.entityId =:id and ec.entityVersion =:version", EntityChange.class)
                    .setParameter("id", entityId)
                    .setParameter("version", entityVersionToFind)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
