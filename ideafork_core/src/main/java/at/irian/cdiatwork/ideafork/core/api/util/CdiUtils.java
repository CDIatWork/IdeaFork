package at.irian.cdiatwork.ideafork.core.api.util;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Set;

public class CdiUtils {
    public static <T> T injectFields(T instance) {
        if (instance == null) {
            return null;
        }

        BeanManager beanManager = resolveBeanManagerViaJndi();

        if (beanManager == null) {
            return instance;
        }

        CreationalContext creationalContext = beanManager.createCreationalContext(null);

        AnnotatedType annotatedType = beanManager.createAnnotatedType(instance.getClass());
        InjectionTarget injectionTarget = beanManager.createInjectionTarget(annotatedType);
        injectionTarget.inject(instance, creationalContext);
        return instance;
    }

    public static <T> T getContextualReference(Class<T> targetClass) {
        BeanManager beanManager = resolveBeanManagerViaJndi();
        Set<Bean<?>> beans = beanManager.getBeans(targetClass);

        if (beans == null || beans.isEmpty() )
        {
            return null;

        }
        Bean<?> resolvedBean = beanManager.resolve(beans);
        CreationalContext<?> creationalContext = beanManager.createCreationalContext(resolvedBean);
        return (T)beanManager.getReference(resolvedBean, targetClass, creationalContext);
    }

    private static BeanManager resolveBeanManagerViaJndi() {
        try {
            return (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            return null;
        }
    }
}
