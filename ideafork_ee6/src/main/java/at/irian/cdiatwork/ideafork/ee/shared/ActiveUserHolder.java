package at.irian.cdiatwork.ideafork.ee.shared;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserActionEvent;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
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
            userActionEvent.fire(new UserActionEvent(new UserAction(AUTO_LOGOUT, user)));
        }
    }
}
