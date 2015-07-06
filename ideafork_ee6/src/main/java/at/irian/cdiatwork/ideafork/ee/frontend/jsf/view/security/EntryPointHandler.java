package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.security;

import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.EntryPoint;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.EntryPointNavigationEvent;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigDescriptor;
import org.apache.deltaspike.core.api.config.view.metadata.ViewConfigResolver;
import org.apache.deltaspike.core.api.scope.WindowScoped;
import org.apache.deltaspike.jsf.api.listener.phase.BeforePhase;
import org.apache.deltaspike.jsf.api.listener.phase.JsfPhaseId;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.faces.component.UIViewRoot;
import javax.faces.event.PhaseEvent;
import javax.inject.Inject;
import java.io.Serializable;

@WindowScoped
public class EntryPointHandler implements Serializable {
    private Class<? extends ViewConfig> previousEntryPoint;

    @Inject
    private ViewConfigResolver viewConfigResolver;

    @Inject
    private Event<EntryPointNavigationEvent> entryPointEvent;

    protected void checkEntryPoints(@Observes @BeforePhase(JsfPhaseId.RENDER_RESPONSE) PhaseEvent phaseEvent) {
        UIViewRoot viewRoot = phaseEvent.getFacesContext().getViewRoot();

        if (viewRoot == null) {
            return;
        }
        String viewIdToRender = viewRoot.getViewId();
        ViewConfigDescriptor viewConfigDescriptor = viewConfigResolver.getViewConfigDescriptor(viewIdToRender);

        if (viewConfigDescriptor == null) {
            return;
        }
        //request to the same (entry-point-)page (= it was rendered previously)
        if (viewConfigDescriptor.getConfigClass().equals(this.previousEntryPoint)) {
            return;
        }

        if (!viewConfigDescriptor.getMetaData(EntryPoint.class).isEmpty()) {
            this.previousEntryPoint = viewConfigDescriptor.getConfigClass();
            this.entryPointEvent.fire(new EntryPointNavigationEvent(viewConfigDescriptor.getConfigClass()));
        }
    }
}