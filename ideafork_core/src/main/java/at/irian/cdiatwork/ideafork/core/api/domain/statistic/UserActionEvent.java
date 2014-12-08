package at.irian.cdiatwork.ideafork.core.api.domain.statistic;

public class UserActionEvent {
    private final UserAction userAction;

    public UserActionEvent(UserAction userAction) {
        this.userAction = userAction;
    }

    public UserAction getUserAction() {
        return userAction;
    }
}
