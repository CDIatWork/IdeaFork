package at.irian.cdiatwork.ideafork.backend.impl.repository.inmemory.role;

import at.irian.cdiatwork.ideafork.backend.api.domain.role.User;
import at.irian.cdiatwork.ideafork.backend.api.monitoring.Monitored;
import at.irian.cdiatwork.ideafork.backend.api.repository.role.UserRepository;
import at.irian.cdiatwork.ideafork.backend.impl.repository.inmemory.GenericInMemoryRepository;

@Monitored
public class UserInMemoryRepository extends GenericInMemoryRepository<User> implements UserRepository {
}
