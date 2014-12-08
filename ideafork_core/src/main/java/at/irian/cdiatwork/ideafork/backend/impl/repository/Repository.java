package at.irian.cdiatwork.ideafork.backend.impl.repository;

import at.irian.cdiatwork.ideafork.backend.api.monitoring.Monitored;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)

@Stereotype
@ApplicationScoped
@Monitored
public @interface Repository {
}
