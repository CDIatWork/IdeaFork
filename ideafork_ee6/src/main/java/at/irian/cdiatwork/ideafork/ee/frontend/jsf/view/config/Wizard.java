package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.config;

import org.apache.deltaspike.core.api.config.view.ViewConfig;
import org.apache.deltaspike.core.api.config.view.metadata.ViewMetaData;
import org.apache.deltaspike.core.spi.config.view.ConfigPreProcessor;
import org.apache.deltaspike.core.spi.config.view.ViewConfigNode;
import org.apache.deltaspike.core.util.metadata.AnnotationInstanceProvider;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE })
@Retention(RUNTIME)
@Documented

@ViewMetaData(preProcessor = Wizard.EntryPointProcessor.class)
public @interface Wizard {
    Class<? extends ViewConfig> entryPoint() default ViewConfig.class;

    class EntryPointProcessor implements ConfigPreProcessor<Wizard> {
        @Override
        public Wizard beforeAddToConfig(Wizard wizard, ViewConfigNode viewConfigNode) {
            if (!ViewConfig.class.equals(wizard.entryPoint()) /*explicitly defined entry-point*/) {
                return wizard;
            }

            for (ViewConfigNode childNode : viewConfigNode.getChildren()) {
                for (Annotation childMetaData : childNode.getMetaData()) {
                    if (EntryPoint.class.equals(childMetaData.annotationType())) {
                        Map<String, Object> values = new HashMap<String, Object>();
                        values.put("entryPoint", childNode.getSource());
                        return AnnotationInstanceProvider.of(Wizard.class, values);
                    }
                }
            }
            return wizard;
        }
    }
}
