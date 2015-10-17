package at.irian.cdiatwork.ideafork.core.impl.config.typed;

import org.apache.deltaspike.partialbean.api.PartialBeanBinding;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@PartialBeanBinding

@Retention(RUNTIME)
@Target(TYPE)
public @interface TypedConfig {
}
