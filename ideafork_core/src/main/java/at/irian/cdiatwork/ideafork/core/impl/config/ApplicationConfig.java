package at.irian.cdiatwork.ideafork.core.impl.config;

import at.irian.cdiatwork.ideafork.core.api.config.ApplicationName;
import at.irian.cdiatwork.ideafork.core.api.config.ApplicationVersion;
import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.impl.config.typed.TypedConfig;

import javax.enterprise.inject.Produces;

@TypedConfig
public interface ApplicationConfig {
    Integer maxNumberOfHighestRatedCategories();

    @Produces
    @ApplicationName
    String name();

    @Produces
    ApplicationVersion version();

    @Produces
    ExternalFormat.TargetFormat defaultExternalFormat();
}
