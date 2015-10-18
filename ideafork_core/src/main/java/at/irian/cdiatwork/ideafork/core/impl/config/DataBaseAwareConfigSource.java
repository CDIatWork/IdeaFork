package at.irian.cdiatwork.ideafork.core.impl.config;

import at.irian.cdiatwork.ideafork.core.api.domain.config.ConfigEntry;
import at.irian.cdiatwork.ideafork.core.api.repository.config.ConfigRepository;
import org.apache.deltaspike.core.spi.config.ConfigSource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.Map;

@ApplicationScoped
public class DataBaseAwareConfigSource implements ConfigSource {
    private final static int ordinal = 2000;

    @Inject
    private ConfigRepository configRepository;

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getPropertyValue(String key) {
        if (key.startsWith("deltaspike.")) {
            return null;
        }

        ConfigEntry configEntry = configRepository.findByEntryKey(key);

        if (configEntry != null) {
            return configEntry.getValue();
        }
        return null;
    }

    @Override
    public String getConfigName() {
        return "config-db";
    }

    @Override
    public boolean isScannable() {
        return false;
    }

    @Override
    public Map<String, String> getProperties() {
        return Collections.emptyMap();
    }
}
