package at.irian.cdiatwork.ideafork.backend.impl.converter;

import at.irian.cdiatwork.ideafork.backend.api.converter.JSON;
import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;

@JSON
@ApplicationScoped
public class JSONConverter implements ObjectConverter {
    @Override
    public <T> T toObject(String value, Class<T> targetType) {
        return new Gson().fromJson(value, targetType);
    }

    @Override
    public String toString(Object entity) {
        return new Gson().toJson(entity);
    }
}
