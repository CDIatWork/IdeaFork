package at.irian.cdiatwork.ideafork.core.impl.monitoring;

import at.irian.cdiatwork.ideafork.core.impl.config.context.ConfigScoped;
import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.inject.Inject;

@ConfigScoped
public class MonitoringConfig {
    @Inject
    @ConfigProperty(name = "methodInvocationThreshold")
    private Integer methodInvocationThreshold;

    public Integer getMethodInvocationThreshold() {
        return methodInvocationThreshold;
    }
}
