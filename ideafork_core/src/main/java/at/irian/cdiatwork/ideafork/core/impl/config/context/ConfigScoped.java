package at.irian.cdiatwork.ideafork.core.impl.config.context;

import javax.enterprise.context.NormalScope;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@NormalScope
@Target({TYPE, METHOD, FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ConfigScoped {
}
