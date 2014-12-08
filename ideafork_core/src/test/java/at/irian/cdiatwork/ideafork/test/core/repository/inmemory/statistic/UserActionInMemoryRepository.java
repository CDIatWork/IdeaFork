package at.irian.cdiatwork.ideafork.test.core.repository.inmemory.statistic;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction;
import at.irian.cdiatwork.ideafork.core.api.repository.statistic.UserActionRepository;
import at.irian.cdiatwork.ideafork.test.core.repository.inmemory.GenericInMemoryRepository;
import at.irian.cdiatwork.ideafork.test.core.repository.inmemory.MockedRepository;

import java.util.ArrayList;
import java.util.List;

@MockedRepository
public class UserActionInMemoryRepository extends GenericInMemoryRepository<UserAction> implements UserActionRepository {
    private static final long serialVersionUID = 2943122589188849100L;

    @Override
    public List<UserAction> loadLatestActivities(User user) {
        List<UserAction> result = new ArrayList<UserAction>();
        for (UserAction userAction : loadAll()) {
            if (userAction.getUser().equals(user)) {
                result.add(userAction);
            }
        }
        return result;
    }
}
