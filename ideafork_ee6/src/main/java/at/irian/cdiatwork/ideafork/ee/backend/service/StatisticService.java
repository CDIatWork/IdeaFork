package at.irian.cdiatwork.ideafork.ee.backend.service;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserActionEvent;
import at.irian.cdiatwork.ideafork.core.api.repository.statistic.UserActionRepository;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class StatisticService {
    @Inject
    private UserActionRepository userActionRepository;

    @Asynchronous
    public void onUserAction(@Observes UserActionEvent userActionEvent) {
        userActionRepository.save(userActionEvent.getUserAction());
    }

    public List<UserAction> loadLatestActivities(User user) {
        return userActionRepository.loadLatestActivities(user);
    }
}
