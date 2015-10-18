package at.irian.cdiatwork.ideafork.ee.backend.init;

import at.irian.cdiatwork.ideafork.core.api.domain.config.ConfigEntry;
import at.irian.cdiatwork.ideafork.core.api.repository.config.ConfigRepository;
import at.irian.cdiatwork.ideafork.ee.infrastructure.DevStartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class DataImporter {
    @Inject
    private ConfigRepository configRepository;

    protected void init(@Observes DevStartupEvent devStartupEvent) {
        configRepository.save(new ConfigEntry("maxNumberOfHighestRatedCategories", "2"));
    }
}
