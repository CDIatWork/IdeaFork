package at.irian.cdiatwork.ideafork.backend.impl.converter;

import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.backend.api.converter.XML;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.io.StringWriter;

@XML
@ApplicationScoped
public class XMLConverter implements ObjectConverter {
    @Override
    public <T> T toObject(String value, Class<T> targetType) {
        return JAXB.unmarshal(new StringReader(value), targetType);
    }

    @Override
    public String toString(Object entity) {
        StringWriter output = new StringWriter();
        JAXB.marshal(entity, output);
        return output.toString();
    }
}
