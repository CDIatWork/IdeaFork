package at.irian.cdiatwork.ideafork.backend.impl.monitoring;

import at.irian.cdiatwork.ideafork.backend.api.config.ApplicationConfig;
import at.irian.cdiatwork.ideafork.backend.api.monitoring.Monitored;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Monitored
@Interceptor
public class MonitoredInterceptor implements Serializable {
    private static final long serialVersionUID = 3503111146284126952L;

    @Inject
    private MonitoredStorage monitoredStorage;

    @Inject
    private ApplicationConfig applicationConfig;

    @AroundInvoke
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

    private boolean isSlowInvocation(long start) {
        return System.currentTimeMillis() - start > applicationConfig.getMethodInvocationThreshold();
    }
}
