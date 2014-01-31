package at.irian.cdiatwork.ideafork.backend.impl.logging;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class IdeaSavedObserver {
    private static final Logger LOGGER = Logger.getLogger(IdeaSavedObserver.class.getName());

    private boolean isIdeaLoggingEnabled;

    @PostConstruct
    protected void init() {
        isIdeaLoggingEnabled = LOGGER.isLoggable(Level.FINE);
    }

    public void onIdeaSavedEvent(@Observes Idea savedIdea) {
        if (isIdeaLoggingEnabled) {
            LOGGER.fine("saved idea: " + savedIdea.getId());
        }
    }
}
