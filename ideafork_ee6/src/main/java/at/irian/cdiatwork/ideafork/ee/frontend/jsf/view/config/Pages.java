package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config;

import org.apache.deltaspike.core.api.config.view.DefaultErrorView;
import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.jsf.api.config.view.View;

import static org.apache.deltaspike.jsf.api.config.view.View.NavigationMode.REDIRECT;

@View(navigation = REDIRECT)
public interface Pages extends ViewConfig {
    class Index implements Pages {}

    interface User extends Pages {
        class Login extends DefaultErrorView {}

        class Registration implements User {}

        class Profile implements User {}
    }

    interface Idea extends Pages {
        class Overview implements Idea {}

        class Create implements Idea {}
        class Edit implements Idea {}

        class List implements Idea {}
        class Details implements Idea {}
    }

    interface Search extends Pages {
        class Fork implements Search {}
    }

    interface Import extends Pages {
        class Upload implements Import {}
        class Summary implements Import {}
    }
}
