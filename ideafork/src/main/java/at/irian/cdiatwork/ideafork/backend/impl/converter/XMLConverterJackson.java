package at.irian.cdiatwork.ideafork.backend.impl.converter;

import at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.IOException;

@Alternative

@ExternalFormat(ExternalFormat.TargetFormat.XML)
@ApplicationScoped
public class XMLConverterJackson implements ObjectConverter {
    @Override
    public <T> T toObject(String value, Class<T> targetType) {
        try {
            return new XmlMapper().readValue(value, targetType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString(Object entity) {
        try {
            return new XmlMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
