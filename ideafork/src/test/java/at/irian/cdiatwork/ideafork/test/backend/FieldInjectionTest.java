package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.backend.api.domain.role.User;
import at.irian.cdiatwork.ideafork.backend.api.domain.role.UserManager;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class FieldInjectionTest {

    @Inject
    private IdeaManager ideaManager;

    @Inject
    private UserManager userManager;

    @Test
    public void ideaCreation() {
        final String topic = "Learn CDI";
        final String category = "Education";
        final String description = "Hello Field-Injection!";

        User author = userManager.createUserFor("os890", null);
        Idea newIdea = this.ideaManager.createIdeaFor(topic, category, author);
        newIdea.setDescription(description);

        Assert.assertEquals(topic, newIdea.getTopic());
        Assert.assertEquals(category, newIdea.getCategory());
        Assert.assertEquals(description, newIdea.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidIdeaCreation() {
        this.ideaManager.createIdeaFor(null, null, null);
    }
}
