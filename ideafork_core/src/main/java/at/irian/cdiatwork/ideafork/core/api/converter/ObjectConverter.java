package at.irian.cdiatwork.ideafork.core.api.converter;

public interface ObjectConverter {
    <T> T toObject(String value, Class<T> targetType);

    String toString(Object entity);
}
