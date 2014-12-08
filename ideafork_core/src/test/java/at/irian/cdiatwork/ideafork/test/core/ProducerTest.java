package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.api.config.ApplicationConfig;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class ProducerTest {

    @Inject
    private ApplicationConfig applicationConfig;

    @Inject
    private ObjectConverter objectConverter;

    @Test
    public void producedBeans() {
        Assert.assertNotNull(applicationConfig);
        Assert.assertNotNull(objectConverter);

        Assert.assertEquals("IdeaFork", applicationConfig.getApplicationName());
    }
}
