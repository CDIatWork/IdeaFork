package at.irian.cdiatwork.ideafork.ee.shared;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import java.io.Serializable;

@SessionScoped
@Model //or just @Named, since @RequestScoped is overruled
public class ActiveUserHolder implements Serializable {
    private User authenticatedUser;

    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    public boolean isLoggedIn() {
        return authenticatedUser != null && !authenticatedUser.isTransient();
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }
}
