package com.epam.jwd.repository;

import com.epam.jwd.exception.EntityNotFoundException;
import com.epam.jwd.holder.EntityHolder;
import com.epam.jwd.model.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class InMemoryEntityRepository<T extends Entity<T>> implements Repository<T> {

    private static final Logger LOG = LogManager.getLogger(InMemoryEntityRepository.class);

    private static final String ENTITY_NOT_FOUND_BY_ID_MSG = "Entity with id %s not found";

    private final EntityHolder<T> entities;
    private int maxId;

    public InMemoryEntityRepository(EntityHolder<T> entities) {
        this.entities = entities;
        this.maxId = 0;
    }

    @Override
    public Optional<T> create(T entity) {
        int id = ++maxId;
        final T entityWithId = entity.withId(id);
        entities.save(entityWithId);
        return Optional.ofNullable(entityWithId);
    }

    private int findMaxId() {
        return entities.size() + 1;
    }

    @Override
    public Optional<T> read(int id) throws EntityNotFoundException {
        final T entity = findEntityById(id);
        if (entity == null) {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_BY_ID_MSG, id));
        }
        return Optional.of(entity);
    }

    @Override
    public Optional<T> update(T entity) throws EntityNotFoundException {
        final T savedEntity = this.read(entity.getId()).get();
        final int entityIndex = entities.indexOf(savedEntity);
        entities.save(entity, entityIndex);
        return Optional.of(entity);
    }

    @Override
    public void delete(int id) {
        try {
            final T entity = this.read(id).get();
            entities.remove(entity);
        } catch (EntityNotFoundException e) {
            LOG.error(e.getMessage(), e);
        }
    }

//    public <E extends Exception> E doSomething() {
//        return null;
//    }

    private T findEntityById(int id) {
        for (T entity : entities) {
            if (entity.getId().equals(id)) {
                return entity;
            }
        }
        return null;
    }

}
