package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class FieldInjectionTest {

    @Inject
    private IdeaManager ideaManager;

    @Test
    public void ideaCreation() {
        final String topic = "Learn CDI";
        final String category = "Education";
        final String description = "Hello Field-Injection!";

        Idea newIdea = this.ideaManager.createIdeaFor(topic, category);
        newIdea.setDescription(description);

        Assert.assertEquals(topic, newIdea.getTopic());
        Assert.assertEquals(category, newIdea.getCategory());
        Assert.assertEquals(description, newIdea.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidIdeaCreation() {
        this.ideaManager.createIdeaFor(null, null);
    }
}
