package at.irian.cdiatwork.ideafork.core.impl.config.context;

import org.apache.deltaspike.core.util.context.AbstractContext;
import org.apache.deltaspike.core.util.context.ContextualStorage;

import javax.enterprise.context.spi.Contextual;
import javax.enterprise.inject.spi.BeanManager;
import java.lang.annotation.Annotation;

public class ConfigContext extends AbstractContext {
    private final ContextualStorage contextualStorage;

    public ConfigContext(BeanManager beanManager) {
        super(beanManager);
        contextualStorage = new ContextualStorage(beanManager, true, isPassivatingScope());
    }

    @Override
    protected ContextualStorage getContextualStorage(Contextual<?> contextual, boolean createIfNotExist) {
        return this.contextualStorage;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return ConfigScoped.class;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    public void reset() {
        AbstractContext.destroyAllActive(this.contextualStorage);
    }
}