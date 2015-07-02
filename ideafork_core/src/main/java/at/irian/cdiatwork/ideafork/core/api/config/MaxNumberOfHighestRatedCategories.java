package at.irian.cdiatwork.ideafork.core.api.config;

import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ConfigProperty(name = "maxNumberOfHighestRatedCategories")
@Target({METHOD, PARAMETER, FIELD})
@Retention(RUNTIME)
@Qualifier
public @interface MaxNumberOfHighestRatedCategories {
    @Nonbinding
    int defaultValue() default 15;
}
