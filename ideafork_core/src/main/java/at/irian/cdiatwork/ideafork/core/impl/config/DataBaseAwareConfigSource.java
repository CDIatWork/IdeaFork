package at.irian.cdiatwork.ideafork.core.impl.config;

import at.irian.cdiatwork.ideafork.core.api.domain.config.ConfigEntry;
import at.irian.cdiatwork.ideafork.core.api.repository.config.ConfigRepository;
import org.apache.deltaspike.core.api.provider.BeanManagerProvider;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.core.spi.config.ConfigSource;

import java.util.Collections;
import java.util.Map;

public class DataBaseAwareConfigSource implements ConfigSource {
    private final static int ordinal = 2000;

    @Override
    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String getPropertyValue(String key) {
        if (!BeanManagerProvider.isActive() || key.startsWith("deltaspike.")) {
            return null;
        }

        ConfigRepository configRepository = BeanProvider.getContextualReference(ConfigRepository.class, true);

        if (configRepository != null) {
            ConfigEntry configEntry = configRepository.findByEntryKey(key);

            if (configEntry != null) {
                return configEntry.getValue();
            }
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
