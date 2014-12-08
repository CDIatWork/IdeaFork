package at.irian.cdiatwork.ideafork.core.impl.repository.decorator.role;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.role.UserChangedEvent;
import at.irian.cdiatwork.ideafork.core.api.repository.role.UserRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.decorator.GenericRepositoryDecorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Decorator
public class UserRepositoryDecorator extends GenericRepositoryDecorator<User>
        implements UserRepository /*in this case optional*/ {
    private static final long serialVersionUID = -6121134755002514436L;

    @Inject
    @Delegate
    private UserRepository delegate;

    @Inject
    private Event<UserChangedEvent> entityChangedEvent;

    protected UserRepository getDelegate() {
        return delegate;
    }

    @Override
    protected void fireEntityChangedEvent(User entity) {
        this.entityChangedEvent.fire(new UserChangedEvent(entity));
    }
}
