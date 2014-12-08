package at.irian.cdiatwork.ideafork.core.api.domain.role;

import at.irian.cdiatwork.ideafork.core.api.repository.role.UserRepository;
import at.irian.cdiatwork.ideafork.core.api.security.PasswordManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
@Typed(UserManager.class)
public class UserManager implements UserRepository {
    private static final long serialVersionUID = -7626090483025497161L;

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordManager passwordManager;

    public User createUserFor(String nickName, String email, String password) {
        return new User(nickName, email, passwordManager.createPasswordHash(password));
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

    @Override
    public User loadByEmail(String email) {
        return userRepository.loadByEmail(email);
    }

    @Override
    public User loadByNickName(String nickName) {
        return userRepository.loadByNickName(nickName);
    }

    @Override
    public List<User> loadAll() {
        return userRepository.loadAll();
    }
}
