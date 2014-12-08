package at.irian.cdiatwork.ideafork.core.api.repository.statistic;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction;
import at.irian.cdiatwork.ideafork.core.api.repository.GenericRepository;

import java.util.List;

public interface UserActionRepository extends GenericRepository<UserAction> {
    List<UserAction> loadLatestActivities(User user);
}
