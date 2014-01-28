package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormatLiteral;
import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.AmbiguousResolutionException;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.UnsatisfiedResolutionException;
import javax.inject.Inject;

import static at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat.TargetFormat.CSV;
import static at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat.TargetFormat.JSON;
import static at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat.TargetFormat.XML;

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
    private IdeaManager ideaManager;

    @Test
    public void jsonConversion() {
        Idea exportedIdea = ideaManager.createIdeaFor(topic, category);
        exportedIdea.setDescription(description);

        Assert.assertFalse(objectConverterJSONInstance.isAmbiguous());
        Assert.assertFalse(objectConverterJSONInstance.isUnsatisfied());

        String jsonString = objectConverterJSONInstance.get().toString(exportedIdea);

        Idea importedIdea = objectConverterJSONInstance.get().toObject(jsonString, Idea.class);

        Assert.assertTrue(exportedIdea.equals(importedIdea));
    }

    @Test
    public void xmlConversion() {
        Idea exportedIdea = ideaManager.createIdeaFor(topic, category);
        exportedIdea.setDescription(description);

        Assert.assertTrue(converterInstance.isAmbiguous());
        Assert.assertFalse(converterInstance.isUnsatisfied());

        String xmlString = converterInstance.select(new ExternalFormatLiteral(XML)).get().toString(exportedIdea);

        Idea importedIdea = converterInstance.select(new ExternalFormatLiteral(XML)).get().toObject(xmlString, Idea.class);

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

        for (ObjectConverter converter : converterInstance) {
            Idea ideaToExport = ideaManager.createIdeaFor(topic, category);

            String exportedIdea = converter.toString(ideaToExport);
            Assert.assertTrue(converter.toObject(exportedIdea, Idea.class).equals(ideaToExport));
            count++;
        }

        Assert.assertEquals(2, count);
    }
}
