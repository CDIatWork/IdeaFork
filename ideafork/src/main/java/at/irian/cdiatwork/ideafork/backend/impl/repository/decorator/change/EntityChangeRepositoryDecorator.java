package at.irian.cdiatwork.ideafork.backend.impl.repository.decorator.change;

import at.irian.cdiatwork.ideafork.backend.api.domain.change.EntityChange;
import at.irian.cdiatwork.ideafork.backend.api.repository.change.EntityChangeRepository;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

//extending GenericRepositoryDecorator is currently only supported by openwebbeans.
@Decorator
public abstract class EntityChangeRepositoryDecorator implements EntityChangeRepository {
    private static final long serialVersionUID = 7022602882157369435L;

    @Inject
    @Delegate
    private EntityChangeRepository delegate;

    @Override
    public void save(EntityChange entity) {
        checkEntity(entity);
        this.delegate.save(entity);
    }

    private void checkEntity(EntityChange entityChange) {
        if (entityChange == null) {
            throw new IllegalArgumentException("'null' as entity isn't allowed");
        }

        if (entityChange.getId() == null) {
            throw new IllegalArgumentException("'null' as entity-id isn't allowed");
        }
    }
}
