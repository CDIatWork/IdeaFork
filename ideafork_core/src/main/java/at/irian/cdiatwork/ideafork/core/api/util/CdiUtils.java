package at.irian.cdiatwork.ideafork.core.api.util;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

    private static BeanManager resolveBeanManagerViaJndi() {
        try {
            return (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
        } catch (NamingException e) {
            return null;
        }
    }
}
