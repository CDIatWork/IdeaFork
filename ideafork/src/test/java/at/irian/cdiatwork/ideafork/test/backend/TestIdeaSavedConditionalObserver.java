package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaChangedEvent;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

import static javax.enterprise.event.Reception.IF_EXISTS;

@RequestScoped
public class TestIdeaSavedConditionalObserver {
    private boolean isEventObserved;

    protected void onIdeaSavedEvent(@Observes(notifyObserver = IF_EXISTS) IdeaChangedEvent savedIdea) {
        this.isEventObserved = true;
    }

    public boolean isEventObserved() {
        return isEventObserved;
    }
}
