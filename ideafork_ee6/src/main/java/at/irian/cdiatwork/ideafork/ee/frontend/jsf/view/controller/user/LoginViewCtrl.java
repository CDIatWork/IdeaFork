package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.user;

import at.irian.cdiatwork.ideafork.ee.backend.service.UserService;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ViewController
public class LoginViewCtrl {
    @Inject
    private UserService userService;

    @Inject
    private ActiveUserHolder userHolder;

    private String email;
    private String password;

    public String login() {
        userService.login(email, password);

        final String message;
        final String navigationTarget;
        FacesMessage.Severity severity = FacesMessage.SEVERITY_INFO;
        if (userHolder.isLoggedIn()) {
            message = "Welcome " + userHolder.getAuthenticatedUser().getNickName() + "!";
            navigationTarget = "/pages/idea/overview.xhtml";
        } else {
            message = "Login failed!";
            severity = FacesMessage.SEVERITY_ERROR;
            navigationTarget = null;
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, message));
        return navigationTarget;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
