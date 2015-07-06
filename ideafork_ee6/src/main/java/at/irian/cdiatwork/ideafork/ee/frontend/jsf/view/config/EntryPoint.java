package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config;

import org.apache.deltaspike.core.api.config.view.metadata.ViewMetaData;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented

@ViewMetaData
public @interface EntryPoint {
}
