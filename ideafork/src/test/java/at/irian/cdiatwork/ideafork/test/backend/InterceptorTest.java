package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.backend.impl.monitoring.MonitoredStorage;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class InterceptorTest {
    @Inject
    private IdeaManager ideaManager;

    @Inject
    private MonitoredStorage monitoredStorage;

    @After
    public void resetInvocationMode() {
        TestMonitoredInterceptorStrategy.activateTestMode(false);
    }

    @Test
    public void normalMethodInvocation() {
        this.ideaManager.createIdeaFor("", "");
        Assert.assertTrue(monitoredStorage.getSlowMethods().isEmpty());
    }

    @Test
    public void slowMethodInvocation() {
        TestMonitoredInterceptorStrategy.activateTestMode(true);
        Assert.assertTrue(monitoredStorage.getSlowMethods().isEmpty());
        this.ideaManager.createIdeaFor("", "");
        Assert.assertFalse(monitoredStorage.getSlowMethods().isEmpty());
    }
}
