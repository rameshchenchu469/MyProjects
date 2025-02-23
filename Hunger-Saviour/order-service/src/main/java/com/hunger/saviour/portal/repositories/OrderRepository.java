package com.hunger.saviour.portal.repositories;

import com.hunger.saviour.portal.entities.OrderEntity;
import com.hunger.saviour.portal.entities.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends MongoRepository<OrderEntity, UUID> {
    List<OrderEntity> findByOrderStatus(OrderStatus orderStatus);
}
