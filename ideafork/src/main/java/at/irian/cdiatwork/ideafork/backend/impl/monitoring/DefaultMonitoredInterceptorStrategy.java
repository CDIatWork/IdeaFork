package at.irian.cdiatwork.ideafork.backend.impl.monitoring;

import at.irian.cdiatwork.ideafork.backend.api.config.ApplicationConfig;
import at.irian.cdiatwork.ideafork.backend.api.monitoring.Monitored;

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
            Monitored monitored = extractMonitoredAnnotation(ic);
            int maxThreshold = monitored.maxThreshold();

            if (maxThreshold < 1) {
                maxThreshold = this.applicationConfig.getMethodInvocationThreshold();
            }

            if (isSlowInvocation(start, maxThreshold)) {
                this.monitoredStorage.recordSlowMethod(ic.getTarget().getClass().getName() + "#" + ic.getMethod().getName());
            }
        }
    }

    private Monitored extractMonitoredAnnotation(InvocationContext ic) {
        Monitored result = ic.getMethod().getAnnotation(Monitored.class);

        if (result == null) {
            result = ic.getTarget().getClass().getAnnotation(Monitored.class);
        }

        if (result == null) { //needed for some versions of weld
            result = ic.getTarget().getClass().getSuperclass().getAnnotation(Monitored.class);
        }
        return result;
    }

    protected boolean isSlowInvocation(long start, int maxThreshold) {
        return System.currentTimeMillis() - start > maxThreshold;
    }
}
