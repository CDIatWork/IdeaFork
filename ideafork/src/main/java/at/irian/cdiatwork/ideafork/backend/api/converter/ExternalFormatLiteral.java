package at.irian.cdiatwork.ideafork.backend.api.converter;

import javax.enterprise.util.AnnotationLiteral;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class ExternalFormatLiteral extends AnnotationLiteral<ExternalFormat> implements ExternalFormat {
    private final TargetFormat value;

    public ExternalFormatLiteral(TargetFormat value) {
        this.value = value;
    }

    @Override
    public TargetFormat value() {
        return this.value;
    }

    @Override
    public String description() {
        return ""; //not needed for CDI because it's @Nonbinding
    }
}
