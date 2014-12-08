package at.irian.cdiatwork.ideafork.core.impl.repository.jpa.role;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.role.UserRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.core.impl.repository.jpa.GenericJpaRepository;

import javax.persistence.NoResultException;

@Repository
public class UserJpaRepository extends GenericJpaRepository<User> implements UserRepository {
    private static final long serialVersionUID = 4408987169957276919L;

    @Override
    public User loadByNickName(String nickName) {
        try {
            return entityManager.createQuery("select u from User u where u.nickName = :nickName", User.class)
                    .setParameter("nickName", nickName).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public User loadByEmail(String email) {
        try {
            return entityManager.createQuery("select u from User u where u.email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
