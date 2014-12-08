package at.irian.cdiatwork.ideafork.backend.impl.monitoring;

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
    private MonitoredInterceptorStrategy interceptorStrategy;

    @AroundInvoke
    public Object intercept(InvocationContext ic) throws Exception {
        return this.interceptorStrategy.intercept(ic);
    }
}
