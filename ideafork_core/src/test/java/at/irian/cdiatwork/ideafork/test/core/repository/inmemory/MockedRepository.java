package at.irian.cdiatwork.ideafork.test.core.repository.inmemory;

import at.irian.cdiatwork.ideafork.core.impl.repository.Repository;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)

@Alternative

@Stereotype
@Repository
public @interface MockedRepository {
}
