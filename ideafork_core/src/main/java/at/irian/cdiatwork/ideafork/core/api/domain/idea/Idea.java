package at.irian.cdiatwork.ideafork.core.api.domain.idea;

import at.irian.cdiatwork.ideafork.core.api.data.view.ExportView;
import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;
import at.irian.cdiatwork.ideafork.core.api.domain.role.User;
import com.fasterxml.jackson.annotation.JsonView;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement //wouldn't be needed if we use jackson only
public class Idea extends BaseEntity {
    private static final long serialVersionUID = -3824813959555007833L;

    private String topic;
    private String category; //specified by the user
    private String description;

    @XmlElementRef //wouldn't be needed if we use jackson only
    private User author;

    private String baseIdeaId;
    private Long baseIdeaVersion;

    private Idea() {
        //needed for data-import
    }

    Idea(String topic, String category, User author) {
        this.topic = topic;
        this.category = category;
        this.author = author;
    }

    Idea(Idea baseIdea, User author) {
        this(baseIdea.getTopic(), baseIdea.getCategory(), author);
        this.description = baseIdea.getDescription();
        this.baseIdeaId = baseIdea.id;
        this.baseIdeaVersion = baseIdea.version;
    }

    /*
     * generated
     */

    @JsonView(ExportView.Public.class)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonView(ExportView.Public.class)
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @JsonView(ExportView.Public.class)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBaseIdeaId() {
        return baseIdeaId;
    }

    public Long getBaseIdeaVersion() {
        return baseIdeaVersion;
    }

    @JsonView(ExportView.Public.class)
    public User getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Idea idea = (Idea) o;

        if (!author.equals(idea.author)) return false;
        if (baseIdeaId != null ? !baseIdeaId.equals(idea.baseIdeaId) : idea.baseIdeaId != null) return false;
        if (baseIdeaVersion != null ? !baseIdeaVersion.equals(idea.baseIdeaVersion) : idea.baseIdeaVersion != null)
            return false;
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
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (baseIdeaId != null ? baseIdeaId.hashCode() : 0);
        result = 31 * result + (baseIdeaVersion != null ? baseIdeaVersion.hashCode() : 0);
        return result;
    }
}
