package at.irian.cdiatwork.ideafork.core.impl.config;

import at.irian.cdiatwork.ideafork.core.api.startup.IdeaForkStartedEvent;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.spi.config.ConfigSource;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import java.util.Arrays;

@ApplicationScoped
public class IdeaForkCoreStartupObserver {
    protected void onStartup(@Observes IdeaForkStartedEvent ideaForkStartedEvent,
                             DataBaseAwareConfigSource configSource) {

        ConfigResolver.addConfigSources(Arrays.<ConfigSource>asList(configSource));
    }
}
