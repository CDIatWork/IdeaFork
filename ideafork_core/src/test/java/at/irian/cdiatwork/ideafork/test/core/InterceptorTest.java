package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.role.UserManager;
import at.irian.cdiatwork.ideafork.core.impl.monitoring.MonitoredStorage;
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

    @Inject
    private UserManager userManager;

    @After
    public void resetInvocationMode() {
        TestMonitoredInterceptorStrategy.activateTestMode(false);
    }

    @Test
    public void normalMethodInvocation() {
        User author = userManager.createUserFor("os890", null);
        this.ideaManager.createIdeaFor("", "", author);
        Assert.assertTrue(monitoredStorage.getSlowMethods().isEmpty());
    }

    @Test
    public void slowMethodInvocation() {
        TestMonitoredInterceptorStrategy.activateTestMode(true);
        Assert.assertTrue(monitoredStorage.getSlowMethods().isEmpty());

        User author = userManager.createUserFor("os890", null);
        this.ideaManager.createIdeaFor("", "", author);
        Assert.assertFalse(monitoredStorage.getSlowMethods().isEmpty());
    }
}
