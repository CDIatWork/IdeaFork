package at.irian.cdiatwork.ideafork.core.impl.config;

import at.irian.cdiatwork.ideafork.core.api.config.ApplicationName;
import at.irian.cdiatwork.ideafork.core.api.config.ApplicationVersion;
import at.irian.cdiatwork.ideafork.core.api.config.MaxNumberOfHighestRatedCategories;
import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.spi.config.BaseConfigPropertyProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class ConfigProducer extends BaseConfigPropertyProducer {
    @Produces
    @ApplicationName
    public String applicationName(InjectionPoint injectionPoint) {
        return getStringPropertyValue(injectionPoint);
    }

    @Produces
    public ApplicationVersion applicationVersion() {
        String configuredValue = ConfigResolver.getPropertyValue("version");
        return new ApplicationVersion(configuredValue);
    }

    @Produces
    @MaxNumberOfHighestRatedCategories
    public Integer maxNumberOfHighestRatedCategories(InjectionPoint injectionPoint) {
        String configuredValue = getStringPropertyValue(injectionPoint);

        if (configuredValue == null || configuredValue.length() == 0) {
            return getAnnotation(injectionPoint, MaxNumberOfHighestRatedCategories.class).defaultValue();
        }

        return Integer.parseInt(configuredValue);
    }
}
