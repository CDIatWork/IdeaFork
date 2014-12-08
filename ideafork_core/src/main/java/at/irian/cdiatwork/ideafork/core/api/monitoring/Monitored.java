package at.irian.cdiatwork.ideafork.core.api.monitoring;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@InterceptorBinding

@Target({TYPE, METHOD})
@Retention(RUNTIME)
public @interface Monitored {
    @Nonbinding
    int maxThreshold() default -1;
}
