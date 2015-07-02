package at.irian.cdiatwork.ideafork.core.impl.config;

import org.apache.deltaspike.core.api.config.PropertyFileConfig;

public class IdeaForkConfigFile implements PropertyFileConfig {
    @Override
    public String getPropertyFileName() {
        return "app-config.properties";
    }

    @Override
    public boolean isOptional() {
        return false;
    }
}
