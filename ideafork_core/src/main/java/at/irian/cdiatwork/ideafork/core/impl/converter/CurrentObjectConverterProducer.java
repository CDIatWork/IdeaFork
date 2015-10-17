package at.irian.cdiatwork.ideafork.core.impl.converter;

import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.core.impl.config.context.ConfigScoped;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;

import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.JSON;
import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.XML;

@ApplicationScoped
public class CurrentObjectConverterProducer {
    @Produces
    @Default
    @ConfigScoped //to create new instances after the config gets reloaded
    protected ObjectConverter defaultConverter(
            @ExternalFormat(XML) ObjectConverter objectConverterXml,
            @ExternalFormat(JSON) ObjectConverter objectConverterJson,
            ExternalFormat.TargetFormat defaultFormat) {
        switch (defaultFormat) {
            case JSON:
                return objectConverterJson;
            default:
                return objectConverterXml;
        }
    }
}
