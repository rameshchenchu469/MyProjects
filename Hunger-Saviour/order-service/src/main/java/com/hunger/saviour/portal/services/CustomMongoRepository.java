package com.hunger.saviour.portal.services;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;
@NoRepositoryBean
public interface CustomMongoRepository <T extends UuidIdentifiedEntity> extends MongoRepository<T, UUID> {
}
