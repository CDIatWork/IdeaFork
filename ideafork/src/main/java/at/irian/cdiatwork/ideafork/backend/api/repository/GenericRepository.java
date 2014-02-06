package at.irian.cdiatwork.ideafork.backend.api.repository;

import at.irian.cdiatwork.ideafork.backend.api.domain.BaseEntity;

import java.io.Serializable;

public interface GenericRepository<T extends BaseEntity> extends Serializable {
    void save(T entity);

    void remove(T entity);

    T loadById(String id);
}