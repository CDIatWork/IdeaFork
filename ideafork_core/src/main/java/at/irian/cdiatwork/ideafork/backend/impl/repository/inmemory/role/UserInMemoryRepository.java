package at.irian.cdiatwork.ideafork.backend.impl.repository.inmemory.role;

import at.irian.cdiatwork.ideafork.backend.api.domain.role.User;
import at.irian.cdiatwork.ideafork.backend.api.repository.role.UserRepository;
import at.irian.cdiatwork.ideafork.backend.impl.repository.Repository;
import at.irian.cdiatwork.ideafork.backend.impl.repository.inmemory.GenericInMemoryRepository;

@Repository
public class UserInMemoryRepository extends GenericInMemoryRepository<User> implements UserRepository {
    private static final long serialVersionUID = 4408987169957276919L;
}
