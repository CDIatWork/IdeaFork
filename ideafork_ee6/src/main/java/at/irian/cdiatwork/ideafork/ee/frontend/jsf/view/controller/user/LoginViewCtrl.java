package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.user;

import at.irian.cdiatwork.ideafork.ee.backend.service.UserService;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.message.UserMessage;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import org.apache.deltaspike.jsf.api.message.JsfMessage;

import javax.inject.Inject;

@ViewController
public class LoginViewCtrl {
    @Inject
    private UserService userService;

    @Inject
    private ActiveUserHolder userHolder;

    @Inject
    private JsfMessage<UserMessage> userMessage;

    private String email;
    private String password;

    public Class<? extends Pages.Idea> login() {
        userService.login(email, password);

        final Class<? extends Pages.Idea> navigationTarget;
        if (userHolder.isLoggedIn()) {
            userMessage.addInfo().welcomeNewUser(userHolder.getAuthenticatedUser().getNickName());
            navigationTarget = Pages.Idea.Overview.class;
        } else {
            userMessage.addError().loginFailed();
            navigationTarget = null;
        }

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
