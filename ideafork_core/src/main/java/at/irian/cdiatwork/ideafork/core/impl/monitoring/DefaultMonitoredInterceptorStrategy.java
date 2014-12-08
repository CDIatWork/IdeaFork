package at.irian.cdiatwork.ideafork.core.impl.monitoring;

import at.irian.cdiatwork.ideafork.core.api.config.ApplicationConfig;
import at.irian.cdiatwork.ideafork.core.api.monitoring.Monitored;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import java.lang.annotation.Annotation;

@Dependent
public class DefaultMonitoredInterceptorStrategy implements MonitoredInterceptorStrategy {
    private static final long serialVersionUID = -8862937799441102864L;

    @Inject
    private MonitoredStorage monitoredStorage;

    @Inject
    private ApplicationConfig applicationConfig;

    @Inject
    private BeanManager beanManager;

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

        if (result != null) {
            return result;
        }

        Class<?> targetClass = ic.getTarget().getClass();

        //needed for some versions of weld
        if (targetClass.getName().startsWith(targetClass.getSuperclass().getName()) &&
                targetClass.getName().contains("$$")) {
            targetClass = targetClass.getSuperclass();
        }

        result = targetClass.getAnnotation(Monitored.class);

        //simplest logic for stereotypes
        if (result == null) {
            for (Annotation annotation : targetClass.getAnnotations()) {
                result = annotation.annotationType().getAnnotation(Monitored.class);

                if (result != null) {
                    return result;
                }
            }
        }

        return result;
    }

    protected boolean isSlowInvocation(long start, int maxThreshold) {
        return System.currentTimeMillis() - start > maxThreshold;
    }
}
