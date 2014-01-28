package at.irian.cdiatwork.ideafork.backend.api.converter;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, FIELD, PARAMETER})
@Retention(RUNTIME)
@Qualifier
public @interface JSON {
}
