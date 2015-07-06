package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.user;

import at.irian.cdiatwork.ideafork.ee.backend.service.UserService;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.message.UserMessage;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.message.JsfMessage;

import javax.inject.Inject;
import java.io.Serializable;

@ViewController
public class RegistrationViewCtrl implements Serializable {
    @Inject
    private UserService userService;

    @Inject
    private JsfMessage<UserMessage> userMessage;

    private User newUser = new User();

    public Class<? extends ViewConfig> register() {
        User registeredUser = userService.registerUser(this.newUser);

        final Class<? extends ViewConfig> targetPage;
        if (registeredUser != null) {
            userMessage.addInfo().registrationSuccessful();
            targetPage = Pages.User.Login.class;
        } else {
            userMessage.addError().registrationFailed();
            targetPage = null;
        }

        return targetPage;
    }

    public User getNewUser() {
        return newUser;
    }
}
