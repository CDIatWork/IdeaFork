package at.irian.cdiatwork.ideafork.backend.api.domain.role;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserManager {
    public User createUserFor(String nickName, String email) {
        return new User(nickName, email);
    }
}
