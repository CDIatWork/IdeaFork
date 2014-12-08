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
public class MethodInjectionTest {

    private IdeaManager ideaManager;

    private UserManager userManager;

    @Inject
    protected void init(IdeaManager ideaManager, UserManager userManager) {
        this.ideaManager = ideaManager;
        this.userManager = userManager;
    }

    @Test
    public void ideaCreation() {
        final String topic = "Learn CDI";
        final String category = "Education";
        final String description = "Hello Method-Injection!";

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
