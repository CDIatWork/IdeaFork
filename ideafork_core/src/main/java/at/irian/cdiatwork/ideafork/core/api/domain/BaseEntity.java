package at.irian.cdiatwork.ideafork.core.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.deltaspike.core.api.exclude.Exclude;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.UUID;

@Exclude
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -7764878761692675990L;

    @Id
    protected String id;

    @Version
    protected Long version;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString().replace("-", "");
    }

    @JsonIgnore
    @Transient
    public boolean isTransient() {
        return version == null;
    }

    public void increaseVersion() {
        if (version == null) {
            version = 0L;
        } else {
            version++;
        }
    }

    /*
     * generated
     */

    public String getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    /*
     * needed for data-import
     */
    protected void setId(String id) {
        this.id = id;
    }

    protected void setVersion(Long version) {
        this.version = version;
    }

    /*
     * generated
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseEntity that = (BaseEntity) o;

        //only compare the id if the entity is persistent
        if (!isTransient()) {
            if (!id.equals(that.id)) return false;
        }
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
