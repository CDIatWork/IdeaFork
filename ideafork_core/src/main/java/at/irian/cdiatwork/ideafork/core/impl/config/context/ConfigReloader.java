package at.irian.cdiatwork.ideafork.core.impl.config.context;

import org.apache.deltaspike.core.api.jmx.JmxManaged;
import org.apache.deltaspike.core.api.jmx.MBean;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

@ApplicationScoped
@MBean(name = "ConfigReloader", category = "IdeaFork")
public class ConfigReloader {
    @Inject
    private BeanManager beanManager;

    @JmxManaged
    public void reloadConfig() {
        ((ConfigContext)beanManager.getContext(ConfigScoped.class)).reset();
    }
}
