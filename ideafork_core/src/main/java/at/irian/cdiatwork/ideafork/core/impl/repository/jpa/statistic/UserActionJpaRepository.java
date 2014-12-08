package at.irian.cdiatwork.ideafork.core.impl.repository.jpa.statistic;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction;
import at.irian.cdiatwork.ideafork.core.api.repository.statistic.UserActionRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.core.impl.repository.jpa.GenericJpaRepository;

import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;

@Repository
public class UserActionJpaRepository extends GenericJpaRepository<UserAction> implements UserActionRepository {
    private static final long serialVersionUID = 2943122589188849100L;

    @Override
    public List<UserAction> loadLatestActivities(User user) {
        try {
            return entityManager.createQuery("select ua from UserAction ua where ua.user = :user order by ua.createdAt desc", UserAction.class)
                    .setParameter("user", user)
                    .setMaxResults(10) //we be done via config like maxNumberOfHighestRatedCategories
                    .getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList();
        }
    }
}
