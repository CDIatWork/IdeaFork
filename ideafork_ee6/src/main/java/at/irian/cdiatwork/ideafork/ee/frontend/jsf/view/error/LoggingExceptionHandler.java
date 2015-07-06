package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.error;

import at.irian.cdiatwork.ideafork.core.api.projectstage.CustomProjectStage;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@ExceptionHandler
public class LoggingExceptionHandler {
    private static final Logger LOG = Logger.getLogger(LoggingExceptionHandler.class.getName());

    public void onUnhandledException(@Handles ExceptionEvent<IOException> exceptionEvent,
                                     ProjectStage projectStage) {

        if (projectStage == CustomProjectStage.Debugging) {
            LOG.log(Level.FINE, "exception detected", exceptionEvent.getException());
        }

        exceptionEvent.throwOriginal();
    }
}
