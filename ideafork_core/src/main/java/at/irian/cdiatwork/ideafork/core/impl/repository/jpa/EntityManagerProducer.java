package at.irian.cdiatwork.ideafork.core.impl.repository.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class EntityManagerProducer {
    @PersistenceContext(name = "ideaForkPU")
    private EntityManager entityManager;

    @Produces
    protected EntityManager exposeEntityManagerProxy() {
        return entityManager;
    }
}
