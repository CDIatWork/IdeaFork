package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller;

import org.apache.deltaspike.core.api.scope.ViewAccessScoped;

import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)

@Stereotype

@ViewAccessScoped
@Named
public @interface ViewController {
}
