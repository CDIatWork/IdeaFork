package at.irian.cdiatwork.ideafork.core.api.config;


import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ConfigProperty(name = "name")
@Target({METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Qualifier
public @interface ApplicationName {
}
