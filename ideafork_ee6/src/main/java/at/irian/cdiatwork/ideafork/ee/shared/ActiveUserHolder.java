package at.irian.cdiatwork.ideafork.ee.shared;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserActionEvent;
import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanManagerProvider;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.core.api.provider.DependentProvider;

import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import static at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction.Type.*;

@Named
@SessionScoped
public class ActiveUserHolder implements Serializable {
    private User authenticatedUser;

    @Inject
    private Event<UserActionEvent> userActionEvent;

    public void setAuthenticatedUser(User authenticatedUser) {
        try {
            if (this.authenticatedUser != null && authenticatedUser == null) {
                onLogout(this.authenticatedUser, true);
            } else {
                onLogin(authenticatedUser);
            }
        } finally {
            this.authenticatedUser = authenticatedUser;
        }
    }

    public boolean isLoggedIn() {
        return authenticatedUser != null && !authenticatedUser.isTransient();
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    @PreDestroy
    protected void onTimeout() {
        onLogout(authenticatedUser, false);
    }

    public void onLogin(User user) {
        userActionEvent.fire(new UserActionEvent(new UserAction(LOGIN, user)));
    }

    public void onLogout(User user, boolean manualLogout) {
        if (manualLogout) {
            userActionEvent.fire(new UserActionEvent(new UserAction(LOGOUT, user)));
        } else {
            //won't work in glassfish 3 due to GLASSFISH-19668

            BeanManager beanManager = BeanManagerProvider.getInstance().getBeanManager();
            DependentProvider<ContextControl> contextControlProvider = BeanProvider.getDependent(beanManager, ContextControl.class);
            try {
                contextControlProvider.get().startContext(RequestScoped.class);
                userActionEvent.fire(new UserActionEvent(new UserAction(AUTO_LOGOUT, user)));
            } finally {
                contextControlProvider.get().stopContext(RequestScoped.class);
                contextControlProvider.destroy();
            }
        }
    }
}
