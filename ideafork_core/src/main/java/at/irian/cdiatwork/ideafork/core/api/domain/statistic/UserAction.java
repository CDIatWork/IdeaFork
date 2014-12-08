package at.irian.cdiatwork.ideafork.core.api.domain.statistic;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
public class UserAction extends BaseEntity {
    @Column
    @Enumerated(STRING)
    private Type userAction;

    @ManyToOne
    private User user;

    @Column
    @Temporal(TIMESTAMP)
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
