package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config;

import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.security.UserAwareAccessDecisionVoter;
import org.apache.deltaspike.core.api.config.view.DefaultErrorView;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.config.view.View;
import org.apache.deltaspike.security.api.authorization.Secured;

import static org.apache.deltaspike.jsf.api.config.view.View.NavigationMode.REDIRECT;

@View(navigation = REDIRECT)
public interface Pages extends ViewConfig {
    class Index implements Pages {}

    @Secured(UserAwareAccessDecisionVoter.class)
    interface SecuredPages extends Pages {}

    interface User extends Pages {
        class Login extends DefaultErrorView {}

        class Registration implements User {}

        class Profile implements SecuredPages {}
    }

    interface Idea extends SecuredPages {
        class Overview implements Idea {}

        class Create implements Idea {}
        class Edit implements Idea {}

        class List implements Idea {}
        class Details implements Idea {}
    }

    interface Search extends SecuredPages {
        class Fork implements Search {}
    }

    interface Import extends SecuredPages {
        class Upload implements Import {}
        class Summary implements Import {}
    }
}
