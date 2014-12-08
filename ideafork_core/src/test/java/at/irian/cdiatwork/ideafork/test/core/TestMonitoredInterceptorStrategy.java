package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.impl.monitoring.DefaultMonitoredInterceptorStrategy;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;

@Specializes
@Dependent
public class TestMonitoredInterceptorStrategy extends DefaultMonitoredInterceptorStrategy {

    private static boolean slowInvocationSimulationModeActive;

    @Override
    protected boolean isSlowInvocation(long start, int maxThreshold) {
        return slowInvocationSimulationModeActive;
    }

    static void activateTestMode(boolean newValue) {
        TestMonitoredInterceptorStrategy.slowInvocationSimulationModeActive = newValue;
    }
}
