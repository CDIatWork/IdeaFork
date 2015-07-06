package at.irian.cdiatwork.ideafork.ee.frontend.jsf.debug;

import org.apache.deltaspike.jsf.api.listener.phase.JsfPhaseListener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import java.util.logging.Logger;

@JsfPhaseListener
public class DebugPhaseListener implements PhaseListener {
    private static final Logger LOG = Logger.getLogger(DebugPhaseListener.class.getName());

    @Override
    public void beforePhase(PhaseEvent event) {
        LOG.info("before " + event.getPhaseId());
    }

    @Override
    public void afterPhase(PhaseEvent event) {
        LOG.info("after " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
