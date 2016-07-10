package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.backend.api.domain.role.User;
import at.irian.cdiatwork.ideafork.backend.api.domain.role.UserManager;
import at.irian.cdiatwork.ideafork.backend.api.repository.idea.IdeaRepository;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class EventTest {

    @Inject
    private IdeaManager ideaManager;

    @Inject
    private IdeaRepository ideaRepository;

    @Inject
    private UserManager userManager;

    @Inject
    private TestIdeaSavedObserver ideaSavedObserver;

    @Inject
    private TestIdeaSavedConditionalObserver conditionalObserver;

    @Test
    @Ignore //will be removed again in the next step
    public void eventDelivery() {
        User author = userManager.createUserFor("os890", null);
        Idea newIdea = this.ideaManager.createIdeaFor("Learn CDI-Events", "Education", author);

        Assert.assertFalse(this.ideaSavedObserver.isEventObserved());
        this.ideaRepository.save(newIdea);
        Assert.assertTrue(this.ideaSavedObserver.isEventObserved());
    }

    @Test
    @Ignore //will be removed again in the next step
    public void conditionalEventDelivery() {
        User author = userManager.createUserFor("os890", null);
        Idea newIdea = this.ideaManager.createIdeaFor("Learn conditional CDI-Events", "Education", author);

        Assert.assertFalse(this.ideaSavedObserver.isEventObserved());
        this.ideaRepository.save(newIdea);
        Assert.assertTrue(this.ideaSavedObserver.isEventObserved());

        Assert.assertFalse(this.conditionalObserver.isEventObserved());
        this.ideaRepository.save(newIdea);
        Assert.assertTrue(this.conditionalObserver.isEventObserved());
    }
}
