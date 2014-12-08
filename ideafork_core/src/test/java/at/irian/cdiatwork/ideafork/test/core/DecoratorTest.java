package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.core.api.domain.change.EntityChange;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.role.UserManager;
import at.irian.cdiatwork.ideafork.core.api.repository.change.EntityChangeRepository;
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
    private EntityChangeRepository entityChangeRepository;

    @Inject
    private ObjectConverter objectConverter;

    @Test
    public void ideaCreationWithPassingGenericDecoratorCheck() {
        Assert.assertNotNull(this.ideaManager);
        Assert.assertNotNull(this.userManager);

        User author = this.userManager.createUserFor("os890", null);
        this.userManager.save(author);
        author = this.userManager.loadById(author.getId());

        Idea newIdea = this.ideaManager.createIdeaFor(topic, category, author);

        this.ideaManager.save(newIdea);

        Idea savedIdea = this.ideaManager.loadById(newIdea.getId());

        Assert.assertEquals(newIdea.getTopic(), savedIdea.getTopic());
        Assert.assertEquals(newIdea.getCategory(), savedIdea.getCategory());
        Assert.assertEquals(newIdea.getDescription(), savedIdea.getDescription());

        EntityChange revision = this.entityChangeRepository.findRevision(savedIdea.getId(), savedIdea.getVersion());
        Assert.assertNotNull(revision);
        Idea archivedIdea = this.objectConverter.toObject(revision.getEntityState(), Idea.class);
        Assert.assertEquals(savedIdea, archivedIdea);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ideaCreationWithFailingGenericDecoratorCheck() {
        this.ideaManager.save(null);
    }
}
