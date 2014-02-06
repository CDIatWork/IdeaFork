package at.irian.cdiatwork.ideafork.backend.api.domain.idea;

import at.irian.cdiatwork.ideafork.backend.api.domain.EntityChangedEvent;

public class IdeaChangedEvent extends EntityChangedEvent<Idea> {
    public IdeaChangedEvent(Idea createdEntity) {
        super(createdEntity);
    }
}
