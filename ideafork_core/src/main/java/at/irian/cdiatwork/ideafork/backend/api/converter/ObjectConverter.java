package at.irian.cdiatwork.ideafork.backend.api.converter;

public interface ObjectConverter {
    <T> T toObject(String value, Class<T> targetType);

    String toString(Object entity);
}
