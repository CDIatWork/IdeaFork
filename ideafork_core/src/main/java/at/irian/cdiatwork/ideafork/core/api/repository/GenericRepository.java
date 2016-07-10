package at.irian.cdiatwork.ideafork.core.api.repository;

import at.irian.cdiatwork.ideafork.core.api.domain.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T extends BaseEntity> extends Serializable {
    void save(T entity);

    void remove(T entity);

    T loadById(String id);

    List<T> loadAll();
}