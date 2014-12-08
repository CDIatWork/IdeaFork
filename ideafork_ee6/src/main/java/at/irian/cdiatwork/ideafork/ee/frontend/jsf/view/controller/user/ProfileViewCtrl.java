package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.user;

import at.irian.cdiatwork.ideafork.ee.backend.service.StatisticService;
import at.irian.cdiatwork.ideafork.core.api.domain.statistic.UserAction;
import at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.controller.ViewController;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@ViewController
public class ProfileViewCtrl {
    @Inject
    private StatisticService statisticService;

    @Inject
    private ActiveUserHolder userHolder;

    private List<UserAction> userActionList;

    @PostConstruct
    public void init() {
        userActionList = statisticService.loadLatestActivities(userHolder.getAuthenticatedUser());
    }

    public List<UserAction> getUserActionList() {
        return userActionList;
    }
}
