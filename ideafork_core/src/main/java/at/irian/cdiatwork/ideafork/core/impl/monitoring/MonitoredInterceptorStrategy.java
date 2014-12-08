package at.irian.cdiatwork.ideafork.core.impl.monitoring;

import javax.interceptor.InvocationContext;
import java.io.Serializable;

public interface MonitoredInterceptorStrategy extends Serializable {
    Object intercept(InvocationContext ic) throws Exception;
}