package at.irian.cdiatwork.ideafork.backend.impl.monitoring;

import javax.interceptor.InvocationContext;
import java.io.Serializable;

public interface MonitoredInterceptorStrategy extends Serializable {
    Object intercept(InvocationContext ic) throws Exception;
}