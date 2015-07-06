package at.irian.cdiatwork.ideafork.ee.backend.service;

import at.irian.cdiatwork.ideafork.core.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.core.api.domain.idea.IdeaManager;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import at.irian.cdiatwork.ideafork.ee.frontend.servlet.ImportSummary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.logging.Logger;

@Stateless
public class FileUploadService {
    private static final Logger LOG = Logger.getLogger(FileUploadService.class.getName());

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Inject
    private IdeaManager ideaManager;

    @Inject
    private ImportSummary importSummary;

    public void storeUploadedFiles(Collection<Part> parts, User user) {
        for (Part part : parts) {
            String fileName = getFileName(part);

            if (fileName == null) { //ignore hidden-field data
                continue;
            }

            LOG.fine("start importing " + fileName);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(part.getInputStream(), UTF8));
                String ideaToImportString = bufferedReader.readLine();

                while (ideaToImportString != null) {
                    try {
                        Idea importedIdea = ideaManager.importIdea(user, ideaToImportString);
                        importSummary.addImportedIdea(importedIdea);
                    } catch (Exception e) {
                        importSummary.addFailedImport(ideaToImportString);
                    }
                    ideaToImportString = bufferedReader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (fileName != null) {
                    importSummary.addFailedImport("Import of '" + fileName + "' failed!");
                } else {
                    importSummary.addFailedImport("Import failed!");
                }
            }
        }
    }

    private String getFileName(Part part) {
        for (String current : part.getHeader("content-disposition").split(";")) {
            current = current.trim();
            if (current.startsWith("filename=")) {
                return current.substring(10, current.length() - 1);
            }
        }
        return null;
    }
}
