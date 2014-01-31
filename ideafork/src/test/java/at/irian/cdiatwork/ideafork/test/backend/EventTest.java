package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.backend.api.repository.idea.IdeaRepository;
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
    private IdeaRepository ideaRepository;

    @Inject
    private TestIdeaSavedObserver ideaSavedObserver;

    @Test
    public void eventDelivery() {
        Idea newIdea = this.ideaManager.createIdeaFor("Learn CDI-Events", "Education");

        Assert.assertFalse(this.ideaSavedObserver.isEventObserved());
        this.ideaRepository.save(newIdea);
        Assert.assertTrue(this.ideaSavedObserver.isEventObserved());
    }
}
