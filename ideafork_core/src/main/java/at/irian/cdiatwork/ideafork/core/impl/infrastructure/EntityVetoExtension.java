package at.irian.cdiatwork.ideafork.core.impl.infrastructure;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.persistence.Entity;

public class EntityVetoExtension implements Extension {
    protected void excludeEntityClasses(@Observes ProcessAnnotatedType pat) {
        if (pat.getAnnotatedType().isAnnotationPresent(Entity.class)) {
            pat.veto();
        }
    }
}

