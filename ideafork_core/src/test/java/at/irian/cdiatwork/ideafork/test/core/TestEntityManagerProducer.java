package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.impl.repository.jpa.EntityManagerProducer;
import org.apache.deltaspike.jpa.api.entitymanager.PersistenceUnitName;
import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Specializes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Specializes
public class TestEntityManagerProducer extends EntityManagerProducer {
    @Inject
    @PersistenceUnitName("ideaForkPU")
    private EntityManagerFactory entityManagerFactory;

    @Produces
    @TransactionScoped
    @Override
    protected EntityManager exposeEntityManagerProxy() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    protected void onTransactionEnd(@Disposes EntityManager entityManager) {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
