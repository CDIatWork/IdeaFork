package at.irian.cdiatwork.ideafork.core.impl.monitoring;

import at.irian.cdiatwork.ideafork.core.impl.config.typed.TypedConfig;

@TypedConfig
public interface MonitoringConfig {
    Integer methodInvocationThreshold();
}
