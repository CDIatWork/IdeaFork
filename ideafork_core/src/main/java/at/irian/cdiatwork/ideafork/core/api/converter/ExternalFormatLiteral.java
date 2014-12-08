package at.irian.cdiatwork.ideafork.core.api.converter;

import javax.enterprise.util.AnnotationLiteral;

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
