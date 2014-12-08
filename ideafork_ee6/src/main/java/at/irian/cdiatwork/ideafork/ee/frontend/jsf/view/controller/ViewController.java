package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)

@Stereotype

//like @Model
@RequestScoped
@Named
public @interface ViewController {
}
