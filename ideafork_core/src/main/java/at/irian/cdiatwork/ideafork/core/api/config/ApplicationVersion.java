package at.irian.cdiatwork.ideafork.core.api.config;

public class ApplicationVersion {
    private final boolean released;
    private final String versionString;

    public ApplicationVersion(String versionString) {
        this.released = !versionString.contains("SNAPSHOT");
        this.versionString = versionString;
    }

    public boolean isReleased() {
        return released;
    }

    @Override
    public String toString() {
        return versionString;
    }
}
