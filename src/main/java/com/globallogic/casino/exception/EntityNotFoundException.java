package com.globallogic.casino.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {


    public EntityNotFoundException(final Class<?> clazz, final Long id) {
        super(String.format("Entity [%s] with id [%d] not found.", clazz.getName(), id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}