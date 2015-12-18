package at.irian.cdiatwork.ideafork.core.impl.config.typed;

import org.apache.deltaspike.core.api.config.ConfigResolver;
import org.apache.deltaspike.core.spi.config.ConfigValidator;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class TypedConfigValidationExtension implements ConfigValidator, Extension {
    private static List<Class> foundConfigClasses = new CopyOnWriteArrayList<Class>();

    public void findTypedConfigClasses(@Observes ProcessAnnotatedType pat) {
        Class<?> beanClass = pat.getAnnotatedType().getJavaClass();
        TypedConfig typedConfig = beanClass.getAnnotation(TypedConfig.class);

        if (typedConfig != null && !InvocationHandler.class.isAssignableFrom(beanClass)) {
            foundConfigClasses.add(beanClass);
        }
    }

    @Override
    public Set<String> processValidation() {
        Set<String> violations = new HashSet<String>();
        for (Class configClass : foundConfigClasses) {
            validateConfigKeys(configClass.getMethods(), violations);
        }

        foundConfigClasses.clear();
        return violations;
    }

    private void validateConfigKeys(Method[] methods, Set<String> violations) {
        for (Method method : methods) {
            String key = method.getName();
            String configuredValue = ConfigResolver.getPropertyValue(key);

            if (configuredValue == null) {
                violations.add("missing config-key: " + key);
            }
        }
    }
}
