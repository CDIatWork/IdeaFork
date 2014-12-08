package at.irian.cdiatwork.ideafork.ee.frontend.servlet;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;

import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class ImportSummary implements Serializable {
    private List<Idea> importedIdeas = new ArrayList<Idea>();
    private List<String> failedImports = new ArrayList<String>();

    public void addImportedIdea(Idea idea) {
        importedIdeas.add(idea);
    }

    public void addFailedImport(String invalidString) {
        failedImports.add(invalidString);
    }

    public boolean isImportedIdeaAvailable() {
        return !importedIdeas.isEmpty();

    }

    public boolean isFailedIdeaAvailable() {
        return !failedImports.isEmpty();
    }

    public List<Idea> getImportedIdeas() {
        return importedIdeas;
    }

    public List<String> getFailedImports() {
        return failedImports;
    }
}
