package at.irian.cdiatwork.ideafork.core.impl.monitoring;

import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class MonitoringConfig {
    @Inject
    @ConfigProperty(name = "methodInvocationThreshold")
    private Integer methodInvocationThreshold;

    public Integer getMethodInvocationThreshold() {
        return methodInvocationThreshold;
    }
}
