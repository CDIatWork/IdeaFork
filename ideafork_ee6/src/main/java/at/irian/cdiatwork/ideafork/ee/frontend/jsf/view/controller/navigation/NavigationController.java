package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.navigation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class NavigationController {
    public String toIdeaImport() {
        return "/pages/import/upload.xhtml";
    }
}
