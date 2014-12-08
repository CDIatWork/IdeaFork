package at.irian.cdiatwork.ideafork.core.impl.repository.inmemory.role;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.repository.role.UserRepository;
import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.core.impl.repository.inmemory.GenericInMemoryRepository;

@Repository
public class UserInMemoryRepository extends GenericInMemoryRepository<User> implements UserRepository {
    private static final long serialVersionUID = 4408987169957276919L;

    @Override
    public User loadByEmail(String email) {
        for (User currentUser : entityMap.values()) {
            if (currentUser.getEmail().equals(email)) {
                return currentUser;
            }
        }

        return null;
    }
}
