package at.irian.cdiatwork.ideafork.core.api.data.view;

public class CategoryView {
    private final String name;
    private long count = 1;

    public CategoryView(String name) {
        this.name = name;
    }

    //can be used for select new queries
    public CategoryView(String name, Long count) {
        this.name = name;
        this.count = count != null ? count : 0;
    }

    public long increaseCount() {
        return count++;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }
}
