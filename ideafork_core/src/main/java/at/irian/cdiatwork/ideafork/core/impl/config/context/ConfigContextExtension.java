package at.irian.cdiatwork.ideafork.core.impl.config.context;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.BeforeShutdown;
import javax.enterprise.inject.spi.Extension;

public class ConfigContextExtension implements Extension {
    public void registerDeltaSpikeContexts(@Observes AfterBeanDiscovery afterBeanDiscovery, BeanManager beanManager) {
        ConfigContext configContext = new ConfigContext(beanManager);
        afterBeanDiscovery.addContext(configContext);
    }

    public void shutdownConfigContext(@Observes BeforeShutdown beforeShutdown, BeanManager beanManager) {
        ((ConfigContext)beanManager.getContext(ConfigScoped.class)).reset();
    }
}
