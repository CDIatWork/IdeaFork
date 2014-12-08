package at.irian.cdiatwork.ideafork.core.api.domain.role;

import at.irian.cdiatwork.ideafork.core.api.repository.role.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;

@ApplicationScoped
@Typed(UserManager.class)
public class UserManager implements UserRepository {
    private static final long serialVersionUID = -7626090483025497161L;

    @Inject
    private UserRepository userRepository;

    public User createUserFor(String nickName, String email) {
        return new User(nickName, email);
    }

    @Override
    public void save(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void remove(User entity) {
        userRepository.remove(entity);
    }

    @Override
    public User loadById(String id) {
        return userRepository.loadById(id);
    }
}
