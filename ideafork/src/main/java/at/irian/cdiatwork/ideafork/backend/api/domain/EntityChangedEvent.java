package at.irian.cdiatwork.ideafork.backend.api.domain;

public class EntityChangedEvent<T extends BaseEntity> {
    private final T entity;
    private final long creationTimestamp;

    public EntityChangedEvent(T entity) {
        this.entity = entity;
        this.creationTimestamp = System.currentTimeMillis();
    }

    public T getEntity() {
        return entity;
    }

    public long getCreationTimestamp() {
        return creationTimestamp;
    }
}
