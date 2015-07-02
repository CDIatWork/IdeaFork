package at.irian.cdiatwork.ideafork.ee.frontend.jsf.view.info;

import at.irian.cdiatwork.ideafork.core.api.config.ApplicationVersion;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

import javax.inject.Inject;
import javax.inject.Named;

import static org.apache.deltaspike.core.api.projectstage.ProjectStage.Staging;

@Named
public class ApplicationInfo {
    private String versionText = "Public";

    @Inject
    public ApplicationInfo(ApplicationVersion appVersion, ProjectStage projectStage) {
        if (projectStage == Staging) {
            if (appVersion.isReleased()) {
                versionText = "Release ";
            }
            versionText += "v" + appVersion.toString();
        }
    }

    public String getVersionText() {
        return versionText;
    }
}
