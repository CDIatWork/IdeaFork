package at.irian.cdiatwork.ideafork.backend.impl.config;

import at.irian.cdiatwork.ideafork.backend.api.config.ApplicationConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.util.ResourceBundle;
import java.util.logging.Logger;

@ApplicationScoped
public class ConfigProducer {
    private static final Logger LOGGER = Logger.getLogger(ConfigProducer.class.getName());

    @Produces
    @ApplicationScoped
    public ApplicationConfig exposeConfig() {
        ResourceBundle config = ResourceBundle.getBundle(getConfigBaseName());
        return new ApplicationConfig(config);
    }

    protected String getConfigBaseName() {
        return "app-config";
    }

    public void onDispose(@Disposes ApplicationConfig applicationConfig) {
        LOGGER.info("shutting down " + applicationConfig.getApplicationName() + " v" + applicationConfig.getApplicationVersion());
    }
}
