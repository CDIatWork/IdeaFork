package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.model;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;

public class SelectableEntity<T extends BaseEntity> {
    private final T entity;
    private boolean selected;

    public SelectableEntity(T entity) {
        this.entity = entity;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public T getEntity() {
        return entity;
    }
}
