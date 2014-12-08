package at.irian.cdiatwork.ideafork.core.impl.repository.inmemory;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;
import at.irian.cdiatwork.ideafork.core.api.repository.GenericRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class GenericInMemoryRepository<T extends BaseEntity> implements GenericRepository<T> {
    protected Map<String, T> entityMap = new ConcurrentHashMap<String, T>();

    @Override
    public void save(T entity) {
        entity.increaseVersion();
        this.entityMap.put(entity.getId(), clone(entity));
    }

    @Override
    public void remove(T entity) {
        this.entityMap.remove(entity.getId());
    }

    @Override
    public T loadById(String id) {
        T originalEntity = this.entityMap.get(id);
        T detachedEntity = null;

        if (originalEntity != null) {
            detachedEntity = clone(originalEntity);
        }
        return detachedEntity;
    }

    @Override
    public List<T> loadAll() {
        List<T> result = new ArrayList<T>();

        for (T currentEntity : this.entityMap.values())
        {
            result.add(clone(currentEntity));
        }
        return result;
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
