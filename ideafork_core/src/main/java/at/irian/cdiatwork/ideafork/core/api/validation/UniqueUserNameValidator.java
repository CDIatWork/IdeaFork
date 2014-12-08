package at.irian.cdiatwork.ideafork.core.api.validation;

import at.irian.cdiatwork.ideafork.core.api.repository.role.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@ApplicationScoped
public class UniqueUserNameValidator implements ConstraintValidator<UserName, String> {
    @Inject
    private UserRepository userRepository;

    public void initialize(UserName differentName) {
    }

    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        return this.userRepository.loadByNickName(userName) == null;
    }
}
