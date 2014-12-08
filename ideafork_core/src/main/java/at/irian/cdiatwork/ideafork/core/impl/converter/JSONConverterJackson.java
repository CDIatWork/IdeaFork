package at.irian.cdiatwork.ideafork.core.impl.converter;

import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExternalFormat(ExternalFormat.TargetFormat.JSON)
@JacksonConverter
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
        return toString(entity, null);
    }

    @Override
    public String toString(Object entity, Class typeSafeDataView) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (typeSafeDataView != null) {
                objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
                return objectMapper.writerWithView(typeSafeDataView).writeValueAsString(entity);
            }
            return objectMapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
