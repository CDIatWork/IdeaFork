package at.irian.cdiatwork.ideafork.core.api.projectstage;

import org.apache.deltaspike.core.api.projectstage.ProjectStage;
import org.apache.deltaspike.core.api.projectstage.ProjectStageHolder;

public class CustomProjectStage implements ProjectStageHolder {
    public static final class Debugging extends ProjectStage {
        private static final long serialVersionUID = -2626602281649294170L;
    }

    public static final Debugging Debugging = new Debugging();
}