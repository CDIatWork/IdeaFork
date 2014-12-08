package at.irian.cdiatwork.ideafork.backend.api.domain.role;

import at.irian.cdiatwork.ideafork.backend.api.domain.EntityChangedEvent;

public class UserChangedEvent extends EntityChangedEvent<User> {
    public UserChangedEvent(User createdEntity) {
        super(createdEntity);
    }
}
