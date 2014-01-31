package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;

@RequestScoped
public class TestIdeaSavedObserver {
    private boolean isEventObserved;

    protected void onIdeaSavedEvent(@Observes Idea savedIdea) {
        this.isEventObserved = true;
    }

    public boolean isEventObserved() {
        return isEventObserved;
    }
}
