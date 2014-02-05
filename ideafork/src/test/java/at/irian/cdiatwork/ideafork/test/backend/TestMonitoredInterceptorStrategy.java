package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.impl.monitoring.DefaultMonitoredInterceptorStrategy;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;

@Specializes
@Dependent
public class TestMonitoredInterceptorStrategy extends DefaultMonitoredInterceptorStrategy {

    private static boolean slowInvocationSimulationModeActive;

    @Override
    protected boolean isSlowInvocation(long start) {
        return slowInvocationSimulationModeActive;
    }

    static void activateTestMode(boolean newValue) {
        TestMonitoredInterceptorStrategy.slowInvocationSimulationModeActive = newValue;
    }
}
