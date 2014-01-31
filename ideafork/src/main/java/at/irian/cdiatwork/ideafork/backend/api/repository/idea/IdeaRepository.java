package at.irian.cdiatwork.ideafork.backend.api.repository.idea;

import at.irian.cdiatwork.ideafork.backend.api.domain.idea.Idea;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.io.*;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IdeaRepository {
    @Inject
    private Event<Idea> ideaSavedEvent;

    protected Map<String, Idea> entityMap = new ConcurrentHashMap<String, Idea>();

    public void save(Idea entity) {
        this.entityMap.put(entity.getId(), clone(entity));
        this.ideaSavedEvent.fire(entity);
    }

    public void remove(Idea entity) {
        this.entityMap.remove(entity.getId());
    }

    public Idea loadById(String id) {
        Idea originalEntity = this.entityMap.get(id);
        Idea detachedEntity = null;

        if (originalEntity != null) {
            detachedEntity = clone(originalEntity);
        }
        return detachedEntity;
    }

    public static <T> T clone(T source) {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(arrayOutputStream);
            outputStream.writeObject(source);
            outputStream.flush();
            ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
            inputStream = new ObjectInputStream(arrayInputStream);
            return (T) inputStream.readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException("you provided an implementation which isn't serializable or" +
                    "implemented as anonymous class" + e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                //noinspection ThrowFromFinallyBlock
                throw new RuntimeException(e);
            }
        }
    }
}
