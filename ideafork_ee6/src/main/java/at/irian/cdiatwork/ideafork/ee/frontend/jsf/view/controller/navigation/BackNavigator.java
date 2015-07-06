package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.navigation;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.navigation.event.PreViewConfigNavigateEvent;
import org.apache.deltaspike.core.api.lifecycle.Destroyed;
import org.apache.deltaspike.core.api.scope.WindowScoped;

import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Stack;

import static javax.enterprise.event.Reception.IF_EXISTS;

@Named
@WindowScoped
public class BackNavigator implements Serializable {
    private Stack<Class<? extends ViewConfig>> previousViewStack = new Stack<Class<? extends ViewConfig>>();
    private boolean backNavigationActive;

    public Class<? extends ViewConfig> toPreviousView() {
        //to avoid that the current view adds itself in case of a wizard it would alternate between the same two wizard-steps
        backNavigationActive = true;
        return previousViewStack.pop();
    }

    protected void onNavigation(@Observes PreViewConfigNavigateEvent navigateEvent) {
        Class<? extends ViewConfig> previousView = navigateEvent.getFromView();
        if (previousView != null && !this.backNavigationActive &&
            (previousViewStack.isEmpty() || !previousViewStack.peek().equals(previousView))) {
            previousViewStack.push(previousView);

            if (previousViewStack.size() > 10) {
                previousViewStack.remove(0);
            }
        }
    }

    protected void onFacesRequestEnd(@Observes(notifyObserver = IF_EXISTS) @Destroyed FacesContext facesContext) {
        this.backNavigationActive = false; //cleanup
    }
}
