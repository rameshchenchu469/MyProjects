package com.hunger.saviour.portal.services;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
public abstract class UuidIdentifiedEntity {

    @Id
    protected UUID id;

    public void setId(UUID id) {

        if (this.id != null) {
            throw new UnsupportedOperationException("ID is already defined");
        }

        this.id = id;
    }
}
