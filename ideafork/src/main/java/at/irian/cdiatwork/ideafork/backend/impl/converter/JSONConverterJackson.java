package at.irian.cdiatwork.ideafork.backend.impl.converter;

import at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.backend.api.monitoring.Monitored;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative

@ExternalFormat(ExternalFormat.TargetFormat.JSON)
@Monitored
@ApplicationScoped
public class JSONConverterJackson implements ObjectConverter {
    @Override
    public <T> T toObject(String value, Class<T> targetType) {
        try {
            return new ObjectMapper().readValue(value, targetType);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String toString(Object entity) {
        try {
            return new ObjectMapper().writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
