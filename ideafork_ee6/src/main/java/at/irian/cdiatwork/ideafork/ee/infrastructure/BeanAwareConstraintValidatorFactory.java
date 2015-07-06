package at.irian.cdiatwork.ideafork.ee.infrastructure;

import org.apache.deltaspike.core.api.provider.BeanProvider;

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
        T managedConstraintValidator = BeanProvider.getContextualReference(validatorClass, true);
        if (managedConstraintValidator == null) {
            managedConstraintValidator = this.defaultFactory.getInstance(validatorClass);
        }
        return managedConstraintValidator;
    }
}
