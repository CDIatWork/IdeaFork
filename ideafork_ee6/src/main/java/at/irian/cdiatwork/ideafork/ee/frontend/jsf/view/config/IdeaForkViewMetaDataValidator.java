package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ConfigDescriptor;
import org.apache.deltaspike.core.spi.config.view.ConfigDescriptorValidator;

import java.util.List;

public class IdeaForkViewMetaDataValidator implements ConfigDescriptorValidator {
    @Override
    public boolean isValid(ConfigDescriptor configDescriptor) {
        List<Wizard> wizardMetaData = configDescriptor.getMetaData(Wizard.class);
        if (wizardMetaData.isEmpty()) {
            return true;
        }

        if (wizardMetaData.size() > 1) {
            throw new IllegalStateException("Aggregation of @" + Wizard.class.getName() + " isn't supported.");
        }

        Wizard wizardAnnotation = wizardMetaData.iterator().next();

        if (ViewConfig.class.equals(wizardAnnotation.entryPoint())) {
            throw new IllegalStateException("No entry-point for wizard " + configDescriptor.getPath() + " found. " +
                "Please use @" + EntryPoint.class + " or @" + Wizard.class.getName() + "#entryPoint");
        }

        return true;
    }
}
