package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config;

import org.apache.deltaspike.core.api.config.view.ViewConfig;

public class EntryPointNavigationEvent {
    private final Class<? extends ViewConfig> view;

    public EntryPointNavigationEvent(Class<? extends ViewConfig> view) {
        this.view = view;
    }

    public Class<? extends ViewConfig> getView() {
        return view;
    }
}
