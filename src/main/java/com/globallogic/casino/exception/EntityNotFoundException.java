package com.globallogic.casino.exception;

import com.globallogic.casino.model.enums.ItemType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public static final String ITEM_TO_SET_NOT_FOUND_MSG = "Requested item to set: %s" +
            " was not found in our inventory.\nYou may need to inform the manager, " +
            "that it needs to be provided.";

    public EntityNotFoundException(final Class<?> clazz, final Long id) {
        super(String.format("Entity [%s] with id [%d] not found.", clazz.getSimpleName(), id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException(String message, ItemType itemType) {
        super(String.format(message, itemType));
    }
}