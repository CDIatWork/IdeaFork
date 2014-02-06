package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.backend.api.domain.role.User;
import at.irian.cdiatwork.ideafork.backend.api.domain.role.UserManager;
import at.irian.cdiatwork.ideafork.backend.api.repository.idea.IdeaRepository;
import at.irian.cdiatwork.ideafork.backend.api.repository.role.UserRepository;
import junit.framework.Assert;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

@RunWith(CdiTestRunner.class)
public class DecoratorTest {
    private final String topic = "Learn CDI-Decorators";
    private final String category = "Education";

    @Inject
    private IdeaManager ideaManager;

    @Inject
    private UserManager userManager;

    @Inject
    private IdeaRepository ideaRepository;

    @Inject
    private UserRepository userRepository;

    @Test
    public void ideaCreationWithPassingGenericDecoratorCheck() {
        Assert.assertNotNull(this.ideaManager);
        Assert.assertNotNull(this.ideaRepository);

        User author = userManager.createUserFor("os890", null);
        userRepository.save(author);
        author = userRepository.loadById(author.getId());

        Idea newIdea = this.ideaManager.createIdeaFor(topic, category, author);

        this.ideaRepository.save(newIdea);

        Idea savedIdea = this.ideaRepository.loadById(newIdea.getId());

        Assert.assertEquals(newIdea.getTopic(), savedIdea.getTopic());
        Assert.assertEquals(newIdea.getCategory(), savedIdea.getCategory());
        Assert.assertEquals(newIdea.getDescription(), savedIdea.getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void ideaCreationWithFailingGenericDecoratorCheck() {
        this.ideaRepository.save(null);
    }
}
