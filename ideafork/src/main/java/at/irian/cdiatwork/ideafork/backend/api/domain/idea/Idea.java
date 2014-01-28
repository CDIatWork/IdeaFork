package at.irian.cdiatwork.ideafork.backend.api.domain.idea;

public class Idea {
    //specified by the user
    private String topic;
    private String category;
    private String description;

    protected Idea() {
        //needed by JAXB
    }

    Idea(String topic, String category) {
        this.topic = topic;
        this.category = category;
    }

    /*
     * generated
     */

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Idea idea = (Idea) o;

        if (!category.equals(idea.category)) return false;
        if (description != null ? !description.equals(idea.description) : idea.description != null) return false;
        if (!topic.equals(idea.topic)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
