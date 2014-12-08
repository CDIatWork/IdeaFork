package at.irian.cdiatwork.ideafork.core.impl.converter;

import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.core.api.monitoring.Monitored;
import com.google.gson.Gson;

import javax.enterprise.context.ApplicationScoped;

@ExternalFormat(ExternalFormat.TargetFormat.JSON)

@Monitored
@ApplicationScoped
public class JSONConverter implements ObjectConverter {
    @Override
    public <T> T toObject(String value, Class<T> targetType) {
        return new Gson().fromJson(value, targetType);
    }

    @Override
    public String toString(Object entity) {
        return toString(entity, null);
    }

    @Override
    public String toString(Object entity, Class typeSafeDataView) {
        return new Gson().toJson(entity);
    }
}
