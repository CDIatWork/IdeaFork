package at.irian.cdiatwork.ideafork.core.api.domain.change;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class EntityChange extends BaseEntity {
    private static final long serialVersionUID = -6314134718971162163L;

    @Column(nullable = false)
    private String entityId; //just for an easier selection

    @Column(nullable = false)
    private long entityVersion;

    @Lob
    @Column(nullable = false)
    private String entityState;

    @Column(nullable = false)
    private long changeTimestamp;

    protected EntityChange() {
        this(null, 0, "", 0);
    }

    public EntityChange(String id, long version, String entityState, long changeTimestamp) {
        this.entityId = id;
        this.entityVersion = version;
        this.entityState = entityState;
        this.changeTimestamp = changeTimestamp;
    }

    public String getEntityId() {
        return entityId;
    }

    public long getEntityVersion() {
        return entityVersion;
    }

    public String getEntityState() {
        return entityState;
    }

    public long getChangeTimestamp() {
        return changeTimestamp;
    }
}
