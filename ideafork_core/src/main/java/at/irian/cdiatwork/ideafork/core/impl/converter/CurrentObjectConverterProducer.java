package at.irian.cdiatwork.ideafork.core.impl.converter;

import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import org.apache.deltaspike.core.api.config.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.JSON;
import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.XML;

@ApplicationScoped
public class CurrentObjectConverterProducer {
    @Produces
    @Default
    @Dependent
    protected ObjectConverter defaultConverter(
            @ExternalFormat(XML) ObjectConverter objectConverterXml,
            @ExternalFormat(JSON) ObjectConverter objectConverterJson,
            @ConfigProperty(name = "defaultExternalFormat") String defaultExternalFormat) {
        switch (ExternalFormat.TargetFormat.valueOf(defaultExternalFormat)) {
            case JSON:
                return objectConverterJson;
            default:
                return objectConverterXml;
        }
    }
}
