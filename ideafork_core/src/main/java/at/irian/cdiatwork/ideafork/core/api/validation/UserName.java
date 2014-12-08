package at.irian.cdiatwork.ideafork.core.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NotNull(groups = Default.class)
@Size(min = 2, max = 9, message = "invalid name", groups = Default.class)

@Constraint(validatedBy = UniqueUserNameValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface UserName
{
    String message() default "user-name exists already";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}