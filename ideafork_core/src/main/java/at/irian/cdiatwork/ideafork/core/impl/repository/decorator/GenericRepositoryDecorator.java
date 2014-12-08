package at.irian.cdiatwork.ideafork.core.impl.repository.decorator;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;
import at.irian.cdiatwork.ideafork.core.api.repository.GenericRepository;

import java.util.List;

public abstract class GenericRepositoryDecorator<T extends BaseEntity> implements GenericRepository<T> {

    protected abstract GenericRepository<T> getDelegate();

    protected abstract void fireEntityChangedEvent(T entity);

    @Override
    public void save(T entity) {
        checkEntity(entity);

        getDelegate().save(entity);

        fireEntityChangedEvent(entity);
    }

    @Override
    public void remove(T entity) {
        checkEntity(entity);
        getDelegate().remove(entity);
    }

    @Override
    public T loadById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("'null' as id isn't allowed");
        }
        return getDelegate().loadById(id);
    }

    @Override
    public List<T> loadAll() {
        return getDelegate().loadAll();
    }

    private void checkEntity(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("'null' as entity isn't allowed");
        }

        if (entity.getId() == null) {
            throw new IllegalArgumentException("'null' as entity-id isn't allowed");
        }
    }
}
