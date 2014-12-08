package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.navigation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class NavigationController {
    public String toNewIdea() {
        return "/pages/idea/create.xhtml";
    }

    public String toIdeaList() {
        return "/pages/idea/list.xhtml";
    }

    public String toIdeaImport() {
        return "/pages/import/upload.xhtml";
    }

    public String toUserProfile() {
        return "/pages/user/profile.xhtml";
    }
}
