package at.irian.cdiatwork.ideafork.backend.api.domain.idea;

public class Idea {
    //specified by the user
    private String topic;
    private String category;
    private String description;

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
}
