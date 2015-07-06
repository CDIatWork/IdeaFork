package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.menu;

import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.spi.scope.window.WindowContext;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

@Named("menuBean")
@Model //or just @RequestScoped, since @Named is overruled
public class MenuController {
    @Inject
    private ActiveUserHolder userHolder;

    @Inject
    private WindowContext windowContext;

    public Class<? extends Pages> home() {
        return Pages.Index.class;
    }

    public Class<Pages.User.Login> login() {
        return Pages.User.Login.class;
    }

    public Class<? extends ViewConfig> logout() {
        resetWindowContext();
        userHolder.setAuthenticatedUser(null);
        return Pages.User.Login.class;
    }

    public Class<? extends ViewConfig> start() {
        if (userHolder.isLoggedIn()) {
            return Pages.Idea.Overview.class;
        }
        return Pages.User.Login.class;
    }

    public Class<? extends Pages.User> register() {
        return Pages.User.Registration.class;
    }

    private void resetWindowContext() {
        String currentWindowId = windowContext.getCurrentWindowId();
        windowContext.closeWindow(currentWindowId);
        windowContext.activateWindow(currentWindowId);
    }
}
