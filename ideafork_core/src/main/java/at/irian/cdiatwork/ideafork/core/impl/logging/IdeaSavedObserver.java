package at.irian.cdiatwork.ideafork.core.impl.logging;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaChangedEvent;
import org.apache.deltaspike.core.api.exclude.Exclude;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Exclude(exceptIfProjectStage = {ProjectStage.Development.class, ProjectStage.UnitTest.class})
public class IdeaSavedObserver {
    private static final Logger LOGGER = Logger.getLogger(IdeaSavedObserver.class.getName());

    private boolean isIdeaLoggingEnabled;

    //just for demo-purposes - with a static isIdeaLoggingEnabled, it can be done with a static block as well
    @PostConstruct
    protected void init() {
        isIdeaLoggingEnabled = LOGGER.isLoggable(Level.FINE);
    }

    public void onIdeaSavedEvent(@Observes IdeaChangedEvent savedIdea) {
        if (isIdeaLoggingEnabled) {
            LOGGER.fine("saved idea: " + savedIdea.getEntity().getId());
        }
    }
}
