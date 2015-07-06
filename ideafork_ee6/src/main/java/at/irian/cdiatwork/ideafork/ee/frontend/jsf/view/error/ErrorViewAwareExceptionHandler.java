package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.error;

import org.apache.deltaspike.core.api.config.view.DefaultErrorView;
import org.apache.deltaspike.core.api.config.view.navigation.ViewNavigationHandler;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.apache.deltaspike.jsf.api.listener.phase.BeforePhase;
import org.apache.deltaspike.jsf.api.listener.phase.JsfPhaseId;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

@RequestScoped
@ExceptionHandler
public class ErrorViewAwareExceptionHandler {
    private boolean exceptionDetected = false;

    public void onUnhandledException(@Handles(ordinal = Integer.MIN_VALUE) ExceptionEvent<IllegalStateException> exceptionEvent) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (facesContext == null) {
            return;
        }

        if (!exceptionEvent.isMarkedHandled()) {
            exceptionEvent.handled();
            exceptionDetected = true;
        }
    }

    protected void navigateOnDetectedException(@Observes @BeforePhase(JsfPhaseId.RENDER_RESPONSE) PhaseEvent phaseEvent,
                                               ViewNavigationHandler viewNavigationHandler) {

        if (exceptionDetected) {
            viewNavigationHandler.navigateTo(DefaultErrorView.class);
        }
    }
}
