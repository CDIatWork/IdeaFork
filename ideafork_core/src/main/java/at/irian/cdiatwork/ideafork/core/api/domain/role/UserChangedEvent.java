package at.irian.cdiatwork.ideafork.core.api.domain.role;

import at.irian.cdiatwork.ideafork.core.api.domain.EntityChangedEvent;

public class UserChangedEvent extends EntityChangedEvent<User> {
    public UserChangedEvent(User createdEntity) {
        super(createdEntity);
    }
}
