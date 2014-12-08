package at.irian.cdiatwork.ideafork.test.core;

import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormatLiteral;
import at.irian.cdiatwork.ideafork.core.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.core.api.domain.role.UserManager;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import java.util.Set;

import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.CSV;
import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.JSON;
import static at.irian.cdiatwork.ideafork.core.api.converter.ExternalFormat.TargetFormat.XML;

@RunWith(CdiTestRunner.class)
public class LookupTest {
    private final String topic = "Learn CDI-Bean-Lookups";
    private final String category = "Education";
    private final String description = "Hello lazy lookup!";

    @Inject
    @ExternalFormat(JSON)
    private Instance<ObjectConverter> objectConverterJSONInstance;

    @Inject
    @Any
    private Instance<ObjectConverter> converterInstance;

    @Inject
    @ExternalFormat(CSV)
    private Instance<ObjectConverter> objectConverterCSVInstance;

    @Inject
    private BeanManager beanManager;

    private IdeaManager ideaManager; //initialized via manual lookup instead of direct injection
    private UserManager userManager; //initialized via manual lookup instead of direct injection

    @Before
    public void init() {
        Set<Bean<?>> imBeans = beanManager.getBeans(IdeaManager.class);
        Bean<?> imBean = beanManager.resolve(imBeans);
        CreationalContext<?> imCreationalContext = beanManager.createCreationalContext(imBean);
        this.ideaManager = (IdeaManager)this.beanManager.getReference(imBean, IdeaManager.class, imCreationalContext);

        Set<Bean<?>> umBeans = beanManager.getBeans(UserManager.class);
        Bean<?> umBean = beanManager.resolve(umBeans);
        CreationalContext<?> umCreationalContext = beanManager.createCreationalContext(umBean);
        this.userManager = (UserManager)this.beanManager.getReference(umBean, UserManager.class, umCreationalContext);
    }

    @Test
    public void jsonConversion() {
        User author = userManager.createUserFor("os890", null);
        Idea exportedIdea = ideaManager.createIdeaFor(topic, category, author);
        exportedIdea.setDescription(description);

        //true with weld in combination with alternatives Assert.assertFalse(objectConverterJSONInstance.isAmbiguous());
        Assert.assertFalse(objectConverterJSONInstance.isUnsatisfied());

        String jsonString = objectConverterJSONInstance.get().toString(exportedIdea);

        Idea importedIdea = objectConverterJSONInstance.get().toObject(jsonString, Idea.class);

        Assert.assertTrue(exportedIdea.equals(importedIdea));
    }

    @Test
    public void xmlConversion() {
        User author = userManager.createUserFor("os890", null);
        Idea exportedIdea = ideaManager.createIdeaFor(topic, category, author);
        exportedIdea.setDescription(description);

        Assert.assertTrue(converterInstance.isAmbiguous());
        Assert.assertFalse(converterInstance.isUnsatisfied());

        String xmlString = converterInstance.select(new ExternalFormatLiteral(XML)).get().toString(exportedIdea);

        Set<Bean<?>> beans = beanManager.getBeans(ObjectConverter.class, new ExternalFormatLiteral(XML));
        Bean<?> bean = beanManager.resolve(beans);
        CreationalContext<?> creationalContext = beanManager.createCreationalContext(bean);

        ObjectConverter xmlConverter = (ObjectConverter)this.beanManager.getReference(bean, ObjectConverter.class, creationalContext);
        Idea importedIdea = xmlConverter.toObject(xmlString, Idea.class);

        Assert.assertTrue(exportedIdea.equals(importedIdea));
    }

    @Test(expected = UnsatisfiedResolutionException.class)
    public void optionalConverter() {
        Assert.assertFalse(objectConverterCSVInstance.isAmbiguous());
        Assert.assertTrue(objectConverterCSVInstance.isUnsatisfied());

        objectConverterCSVInstance.get();
    }

    @Test(expected = AmbiguousResolutionException.class)
    public void ambiguousConverter() {
        Assert.assertTrue(converterInstance.isAmbiguous());
        Assert.assertFalse(converterInstance.isUnsatisfied());

        converterInstance.get();
    }

    @Test
    public void allConverters() {
        Assert.assertTrue(converterInstance.isAmbiguous());
        Assert.assertFalse(converterInstance.isUnsatisfied());

        int count = 0;
        User author = userManager.createUserFor("os890", null);

        for (ObjectConverter converter : converterInstance) {
            Idea ideaToExport = ideaManager.createIdeaFor(topic, category, author);

            String exportedIdea = converter.toString(ideaToExport);
            Assert.assertTrue(converter.toObject(exportedIdea, Idea.class).equals(ideaToExport));
            count++;
        }

        Assert.assertTrue(count > 0);
        //Assert.assertEquals(3, count); //different behaviour
    }
}
