package at.irian.cdiatwork.ideafork.backend.impl.change;

import at.irian.cdiatwork.ideafork.backend.api.converter.ObjectConverter;
import at.irian.cdiatwork.ideafork.backend.api.domain.change.EntityChange;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;
import at.irian.cdiatwork.ideafork.backend.api.domain.idea.IdeaChangedEvent;
import at.irian.cdiatwork.ideafork.backend.api.repository.change.EntityChangeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@ApplicationScoped
public class IdeaHistoryProcessor {
    @Inject
    @Default
    private ObjectConverter currentObjectConverter;

    @Inject
    private EntityChangeRepository entityChangeRepository;

    public void onIdeaCreated(@Observes IdeaChangedEvent changedEvent) {
        Idea entity = changedEvent.getEntity();
        String ideaSnapshot = currentObjectConverter.toString(entity);
        EntityChange entityChange = new EntityChange(
                entity.getId(),
                entity.getVersion(),
                ideaSnapshot,
                changedEvent.getCreationTimestamp());

        entityChangeRepository.save(entityChange);
    }
}
