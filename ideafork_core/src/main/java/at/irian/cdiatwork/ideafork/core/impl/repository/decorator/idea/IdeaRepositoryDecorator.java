package at.irian.cdiatwork.ideafork.core.impl.repository.decorator.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaChangedEvent;
import at.irian.cdiatwork.ideafork.core.api.repository.idea.IdeaRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.decorator.GenericRepositoryDecorator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Decorator
public class IdeaRepositoryDecorator extends GenericRepositoryDecorator<Idea>
        implements IdeaRepository /*in this case optional*/ {
    private static final long serialVersionUID = -6978288428275696404L;

    @Inject
    @Delegate
    private IdeaRepository delegate;

    @Inject
    private Event<IdeaChangedEvent> entityChangedEvent;

    protected IdeaRepository getDelegate() {
        return delegate;
    }

    @Override
    protected void fireEntityChangedEvent(Idea entity) {
        this.entityChangedEvent.fire(new IdeaChangedEvent(entity));
    }
}
