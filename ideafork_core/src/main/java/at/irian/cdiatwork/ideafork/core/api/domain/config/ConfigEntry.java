package at.irian.cdiatwork.ideafork.core.api.domain.config;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class ConfigEntry extends BaseEntity {
    private static final long serialVersionUID = -3314134718971162163L;

    @Column(unique = true, nullable = false)
    private String entryKey;

    @Column
    private String value;

    protected ConfigEntry() {
    }

    public ConfigEntry(String entryKey, String value) {
        this.entryKey = entryKey;
        this.value = value;
    }

    /*
     * generated
     */

    public String getEntryKey() {
        return entryKey;
    }

    public void setEntryKey(String entryKey) {
        this.entryKey = entryKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
