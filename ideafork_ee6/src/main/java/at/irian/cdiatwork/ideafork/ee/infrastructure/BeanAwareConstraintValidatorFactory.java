package at.irian.cdiatwork.ideafork.ee.infrastructure;

import at.irian.cdiatwork.ideafork.core.api.util.CdiUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;

public class BeanAwareConstraintValidatorFactory implements ConstraintValidatorFactory {
    private final ConstraintValidatorFactory defaultFactory;

    public BeanAwareConstraintValidatorFactory() {
        defaultFactory = Validation.byDefaultProvider().configure().getDefaultConstraintValidatorFactory();
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> validatorClass) {
        T managedConstraintValidator = CdiUtils.getContextualReference(validatorClass);
        if (managedConstraintValidator == null) {
            managedConstraintValidator = this.defaultFactory.getInstance(validatorClass);
        }
        return managedConstraintValidator;
    }
}
