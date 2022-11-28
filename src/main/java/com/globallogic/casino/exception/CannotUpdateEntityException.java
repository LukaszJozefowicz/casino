package com.globallogic.casino.exception;

public class CannotUpdateEntityException extends RuntimeException {
    public static final String EMPLOYEE_ALREADY_ASSIGNED_MSG = "Cannot assign employee to game with id: %d." +
            " Employee is already assigned there.";

    public CannotUpdateEntityException(final Long gameId, String message)  {
        super(String.format(message, gameId));
    }
}
