package at.irian.cdiatwork.ideafork.backend.impl.monitoring;

import at.irian.cdiatwork.ideafork.backend.api.config.ApplicationConfig;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;

@Dependent
public class DefaultMonitoredInterceptorStrategy implements MonitoredInterceptorStrategy {
    private static final long serialVersionUID = -8862937799441102864L;

    @Inject
    private MonitoredStorage monitoredStorage;

    @Inject
    private ApplicationConfig applicationConfig;

    @Override
    public Object intercept(InvocationContext ic) throws Exception {
        long start = System.currentTimeMillis();

        try {
            return ic.proceed();
        } finally {
            if (isSlowInvocation(start)) {
                this.monitoredStorage.recordSlowMethod(ic.getTarget().getClass().getName() + "#" + ic.getMethod().getName());
            }
        }
    }

    protected boolean isSlowInvocation(long start) {
        return System.currentTimeMillis() - start > applicationConfig.getMethodInvocationThreshold();
    }
}
