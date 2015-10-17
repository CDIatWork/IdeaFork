package at.irian.cdiatwork.ideafork.core.impl.config.typed;

import at.irian.cdiatwork.ideafork.core.api.config.ApplicationVersion;
import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.impl.config.context.ConfigScoped;
import org.apache.deltaspike.core.api.config.ConfigResolver;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@TypedConfig
@ConfigScoped
@SuppressWarnings("unused")
public class TypedConfigHandler implements InvocationHandler {
    private Map<String, Object> loadedValues = new ConcurrentHashMap<String, Object>();

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String key = method.getName();
        Object result = loadedValues.get(key);

        if (result != null) {
            return result;
        }

        String loadedValue = ConfigResolver.getProjectStageAwarePropertyValue(key);

        final Class<?> configType = method.getReturnType();
        result = parseValue(loadedValue, configType);

        loadedValues.put(key, result);
        return result;
    }

    private Object parseValue(String loadedValue, Class<?> configType) {
        if (loadedValue != null) {
            if (configType.equals(Integer.class)) {
                return Integer.parseInt(loadedValue);
            } else if (configType.equals(String.class)) {
                return loadedValue;
            } else if (configType.equals(ApplicationVersion.class)) {
                return new ApplicationVersion(loadedValue);
            } else if (configType.equals(ExternalFormat.TargetFormat.class)) {
                return ExternalFormat.TargetFormat.valueOf(loadedValue);
            } else {
                throw new IllegalStateException(configType.getName() + " isn't supported");
            }
        }
        return null;
    }
}
