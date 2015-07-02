package at.irian.cdiatwork.ideafork.core.api.config;

import javax.enterprise.inject.Typed;
import java.util.ResourceBundle;

@Typed()
public class ApplicationConfig {
    private String applicationName;
    private ApplicationVersion applicationVersion; //just to illustrate a type-safe part as well
    private int methodInvocationThreshold;
    private int maxNumberOfHighestRatedCategories;

    protected ApplicationConfig() {
        //needed for creating a proxy
    }

    public ApplicationConfig(ResourceBundle config) {
        this.applicationName = config.getString("name");
        this.applicationVersion = new ApplicationVersion(config.getString("version"));
        this.methodInvocationThreshold = Integer.parseInt(config.getString("methodInvocationThreshold"));
        this.maxNumberOfHighestRatedCategories = Integer.parseInt(config.getString("maxNumberOfHighestRatedCategories"));
    }

    public String getApplicationName() {
        return applicationName;
    }

    public ApplicationVersion getApplicationVersion() {
        return applicationVersion;
    }

    public int getMethodInvocationThreshold() {
        return methodInvocationThreshold;
    }

    public int getMaxNumberOfHighestRatedCategories() {
        return maxNumberOfHighestRatedCategories;
    }

    public static class ApplicationVersion {
        private final int major;
        private final int minor;
        private final String versionString;

        public ApplicationVersion(String versionString) {
            this(Integer.parseInt(versionString.substring(0, versionString.indexOf("."))),
                    Integer.parseInt(versionString.substring(versionString.indexOf(".") + 1)));
        }

        public ApplicationVersion(int major, int minor) {
            this.major = major;
            this.minor = minor;
            this.versionString = this.major + "." + this.minor;
        }

        public int getMajor() {
            return major;
        }

        public int getMinor() {
            return minor;
        }

        @Override
        public String toString() {
            return versionString;
        }
    }
}
