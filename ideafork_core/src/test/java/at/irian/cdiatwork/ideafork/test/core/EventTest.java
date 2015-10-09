package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.role.UserManager;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class EventTest {

    @Inject
    private IdeaManager ideaManager;

    @Inject
    private UserManager userManager;

    @Inject
    private TestIdeaSavedObserver ideaSavedObserver;

    @Inject
    private TestIdeaSavedConditionalObserver conditionalObserver;

    @Test
    public void eventDelivery() {
        User author = userManager.createUserFor("os890", null, "test");
        this.userManager.save(author);

        Idea newIdea = this.ideaManager.createIdeaFor("Learn CDI-Events", "Education", author);

        Assert.assertFalse(this.ideaSavedObserver.isEventObserved());
        this.ideaManager.save(newIdea);
        Assert.assertTrue(this.ideaSavedObserver.isEventObserved());
    }

    @Test
    public void conditionalEventDelivery() {
        User author = userManager.createUserFor("os890", null, "test");
        this.userManager.save(author);

        Idea newIdea = this.ideaManager.createIdeaFor("Learn conditional CDI-Events", "Education", author);

        Assert.assertFalse(this.ideaSavedObserver.isEventObserved());
        this.ideaManager.save(newIdea);
        Assert.assertTrue(this.ideaSavedObserver.isEventObserved());

        Assert.assertFalse(this.conditionalObserver.isEventObserved());
        this.ideaManager.save(newIdea);
        Assert.assertTrue(this.conditionalObserver.isEventObserved());
    }
}
