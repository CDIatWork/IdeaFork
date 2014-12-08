package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaChangedEvent;
import at.irian.cdiatwork.ideafork.core.impl.logging.IdeaSavedObserver;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Specializes;

@Specializes
@RequestScoped
public class TestIdeaSavedObserver extends IdeaSavedObserver {
    private boolean isEventObserved;

    @Override
    public void onIdeaSavedEvent(@Observes IdeaChangedEvent savedIdea) {
        super.onIdeaSavedEvent(savedIdea);
        this.isEventObserved = true;
    }

    public boolean isEventObserved() {
        return isEventObserved;
    }
}
