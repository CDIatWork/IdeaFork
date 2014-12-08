package at.irian.cdiatwork.ideafork.core.api.domain.statistic;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;

import java.util.Date;

public class UserAction extends BaseEntity {
    private Type userAction;
    private User user;
    private Date createdAt = new Date();

    protected UserAction() {
    }

    public UserAction(Type userAction, User user) {
        this.userAction = userAction;
        this.user = user;
    }

    public Type getUserAction() {
        return userAction;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public static enum  Type {
        LOGIN, LOGOUT, AUTO_LOGOUT
    }
}
