package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.navigation;

import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config.Pages;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class NavigationController {
    public Class<? extends Pages.Idea> toNewIdea() {
        return Pages.Idea.Create.class;
    }

    public Class<? extends Pages.Idea> toIdeaList() {
        return Pages.Idea.List.class;
    }

    public Class<? extends Pages.Import> toIdeaImport() {
        return Pages.Import.Upload.class;
    }

    public Class<? extends Pages.SecuredPages> toUserProfile() {
        return Pages.User.Profile.class;
    }
}
