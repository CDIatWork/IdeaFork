package at.irian.cdiatwork.ideafork.core.impl.converter;

import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.core.api.monitoring.Monitored;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.io.StringWriter;

@ExternalFormat(ExternalFormat.TargetFormat.XML)

@Monitored
@ApplicationScoped
public class XMLConverter implements ObjectConverter {
    @Override
    public <T> T toObject(String value, Class<T> targetType) {
        return JAXB.unmarshal(new StringReader(value), targetType);
    }

    @Override
    public String toString(Object entity) {
        return toString(entity, null);
    }

    @Override
    public String toString(Object entity, Class typeSafeDataView) {
        StringWriter output = new StringWriter();
        JAXB.marshal(entity, output);
        return output.toString();
    }
}
