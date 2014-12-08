package at.irian.cdiatwork.ideafork.core.api.domain.idea;

import at.irian.cdiatwork.ideafork.core.api.domain.EntityChangedEvent;

public class IdeaChangedEvent extends EntityChangedEvent<Idea> {
    public IdeaChangedEvent(Idea createdEntity) {
        super(createdEntity);
    }
}
