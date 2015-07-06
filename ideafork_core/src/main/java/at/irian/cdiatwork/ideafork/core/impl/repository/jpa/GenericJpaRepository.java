package at.irian.cdiatwork.ideafork.core.impl.repository.jpa;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;
import at.irian.cdiatwork.ideafork.core.api.repository.GenericRepository;
import org.apache.deltaspike.core.util.ProxyUtils;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class GenericJpaRepository<T extends BaseEntity> implements GenericRepository<T> {

    protected final Class<? extends BaseEntity> entityClass;

    protected GenericJpaRepository() {
        entityClass = detectConcreteEntityType();
    }

    @Inject
    protected EntityManager entityManager;

    @Override
    public void save(T entity) {
        if (entity.isTransient()) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    @Override
    public void remove(T entity) {
        //TODO update forked entities
        if (entity.isTransient()) {
            throw new IllegalStateException("entity is not persistent");
        }

        entityManager.remove(loadById(entity.getId()));
    }

    @Override
    public T loadById(String id) {
        return (T) entityManager.find(entityClass, id);
    }

    @Override
    public List<T> loadAll() {
        return (List<T>) entityManager.createQuery("select entity from " + entityClass.getSimpleName() + " entity")
                .getResultList();
    }

    private Class<? extends BaseEntity> detectConcreteEntityType() {
        Class currentClass = ProxyUtils.getUnproxiedClass(getClass());

        for (Type interfaceClass : currentClass.getGenericInterfaces()) {
            for (Type genericInterfaceClass : ((Class) interfaceClass).getGenericInterfaces()) {
                if (genericInterfaceClass instanceof ParameterizedType &&
                        GenericRepository.class.isAssignableFrom((Class) ((ParameterizedType) genericInterfaceClass).getRawType())) {
                    for (Type parameterizedType : ((ParameterizedType) genericInterfaceClass).getActualTypeArguments()) {
                        if (BaseEntity.class.isAssignableFrom((Class) parameterizedType)) {
                            return (Class<? extends BaseEntity>) parameterizedType;
                        }
                    }
                }
            }
        }

        throw new IllegalStateException("Entity type of " + currentClass.getName() + " not detected!");
    }
}
