package at.irian.cdiatwork.ideafork.core.impl.monitoring;

import javax.enterprise.context.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class MonitoredStorage {
    private List<String> slowMethods = new ArrayList<String>();

    public void recordSlowMethod(String name) {
        this.slowMethods.add(name);
    }

    public List<String> getSlowMethods() {
        return slowMethods;
    }
}
