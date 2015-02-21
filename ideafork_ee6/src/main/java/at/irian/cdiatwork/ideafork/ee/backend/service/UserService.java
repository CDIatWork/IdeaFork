package at.irian.cdiatwork.ideafork.ee.backend.service;

import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.role.UserManager;
import at.irian.cdiatwork.ideafork.core.api.security.PasswordManager;
import at.irian.cdiatwork.ideafork.ee.shared.ActiveUserHolder;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    @Inject
    private UserManager userManager;

    @Inject
    private PasswordManager passwordManager;

    @Inject
    private ActiveUserHolder userHolder;

    public User registerUser(User newUser) {
        if (userManager.loadByEmail(newUser.getEmail()) == null) {
            newUser.setPassword(passwordManager.createPasswordHash(newUser.getPassword()));
            userManager.save(newUser);
            return userManager.loadById(newUser.getId());
        }
        return null;
    }

    public void login(String email, String password) {
        User registeredUser = userManager.loadByEmail(email);

        if (registeredUser != null) {
            String hashedPassword = passwordManager.createPasswordHash(password);

            if (hashedPassword.equals(registeredUser.getPassword())) {
                userHolder.setAuthenticatedUser(registeredUser);
                return;
            }
        }

        userHolder.setAuthenticatedUser(null);
    }
}
