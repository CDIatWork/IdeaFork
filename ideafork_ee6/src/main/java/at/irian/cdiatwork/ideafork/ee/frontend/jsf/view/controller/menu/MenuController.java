package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.menu;

import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

@Named("menuBean")
@Model //or just @RequestScoped, since @Named is overruled
public class MenuController {
    @Inject
    private ActiveUserHolder userHolder;

    public String home() {
        return "/pages/index.xhtml";
    }

    public String login() {
        return "/pages/user/login.xhtml";
    }

    public String logout() {
        userHolder.setAuthenticatedUser(null);
        return "/pages/user/login.xhtml";
    }

    public String start() {
        if (userHolder.isLoggedIn()) {
            return "/pages/idea/overview.xhtml";
        }
        return "/pages/user/login.xhtml";
    }

    public String register() {
        return "/pages/user/registration.xhtml";
    }
}
