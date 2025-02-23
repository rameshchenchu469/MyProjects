package com.hunger.saviour.portal.services;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import java.util.UUID;

public class CustomMongoRepositoryImpl <T extends UuidIdentifiedEntity>
        extends SimpleMongoRepository<T, UUID> implements CustomMongoRepository<T> {
    public CustomMongoRepositoryImpl(MongoEntityInformation<T, UUID> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    @Override
    public <S extends T> S save(S entity) {
        generateId(entity);
        return super.save(entity);
    }

    protected <S extends T> void generateId(S entity) {

        if(entity != null && entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}
