package at.irian.cdiatwork.ideafork.test.backend;

import at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat;
import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaManager;
import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat.TargetFormat.JSON;
import static at.irian.cdiatwork.ideafork.backend.api.converter.ExternalFormat.TargetFormat.XML;

@RunWith(CdiTestRunner.class)
public class QualifierTest {
    private final String topic = "Learn CDI-Qualifiers";
    private final String category = "Education";
    private final String description = "Hello Qualifiers!";

    @Inject
    @ExternalFormat(JSON)
    private ObjectConverter objectConverterJSON;

    @Inject
    @ExternalFormat(XML)
    private ObjectConverter objectConverterXML;

    @Inject
    private IdeaManager ideaManager;

    @Test
    public void jsonConversion() {
        Idea exportedIdea = ideaManager.createIdeaFor(topic, category);
        exportedIdea.setDescription(description);

        String jsonString = objectConverterJSON.toString(exportedIdea);

        Idea importedIdea = objectConverterJSON.toObject(jsonString, Idea.class);

        Assert.assertTrue(exportedIdea.equals(importedIdea));
    }

    @Test
    public void xmlConversion() {
        Idea exportedIdea = ideaManager.createIdeaFor(topic, category);
        exportedIdea.setDescription(description);

        String xmlString = objectConverterXML.toString(exportedIdea);

        Idea importedIdea = objectConverterXML.toObject(xmlString, Idea.class);

        Assert.assertTrue(exportedIdea.equals(importedIdea));
    }

    @Test
    public void exportToJson() {
        Idea newIdea = ideaManager.createIdeaFor(topic, category);
        newIdea.setDescription(description);

        String jsonString = objectConverterJSON.toString(newIdea);

        Assert.assertTrue(jsonString.contains(topic));
        Assert.assertTrue(jsonString.contains(category));
        Assert.assertTrue(jsonString.contains(description));

        Assert.assertTrue(jsonString.contains("topic"));
        Assert.assertTrue(jsonString.contains("category"));
        Assert.assertTrue(jsonString.contains("description"));
    }

    @Test
    public void exportToXml() {
        Idea newIdea = ideaManager.createIdeaFor(topic, category);
        newIdea.setDescription(description);

        String xmlString = objectConverterXML.toString(newIdea);

        Assert.assertTrue(xmlString.contains(topic));
        Assert.assertTrue(xmlString.contains(category));
        Assert.assertTrue(xmlString.contains(description));

        Assert.assertTrue(xmlString.contains("<topic>"));
        Assert.assertTrue(xmlString.contains("</topic>"));
        Assert.assertTrue(xmlString.contains("<category>"));
        Assert.assertTrue(xmlString.contains("</category>"));
        Assert.assertTrue(xmlString.contains("<description>"));
        Assert.assertTrue(xmlString.contains("</description>"));
    }

    @Test
    public void importJson() {
        String jsonString = "{\"topic\":\"" + topic + "\",\"category\":\"" + category + "\",\"description\":\"" + description + "\",\"id\":\"xyz\"}";

        Idea idea = objectConverterJSON.toObject(jsonString, Idea.class);
        Assert.assertEquals(topic, idea.getTopic());
        Assert.assertEquals(category, idea.getCategory());
        Assert.assertEquals(description, idea.getDescription());
    }

    @Test
    public void importXml() {
        String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
                "<idea><id>xyz</id><topic>" + topic + "</topic><category>" + category + "</category><description>" + description + "</description></idea>";

        Idea idea = objectConverterXML.toObject(xmlString, Idea.class);
        Assert.assertEquals(topic, idea.getTopic());
        Assert.assertEquals(category, idea.getCategory());
        Assert.assertEquals(description, idea.getDescription());
    }
}
