package at.irian.cdiatwork.ideafork.backend.impl.converter;

import at.irian.cdiatwork.ideafork.backend.api.config.ApplicationConfig;
import at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import static at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat.TargetFormat.*;

@ApplicationScoped
public class CurrentObjectConverterProducer {
    @Produces
    @Default
    @Dependent
    protected ObjectConverter defaultConverter(
            @ExternalFormat(XML) ObjectConverter objectConverterXml,
            @ExternalFormat(JSON) ObjectConverter objectConverterJson,
            ApplicationConfig applicationConfig) {
        switch (applicationConfig.getDefaultExternalFormat()) {
            case JSON:
                return objectConverterJson;
            default:
                return objectConverterXml;
        }
    }
}
