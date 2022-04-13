package ru.hj77.client.exception;

import lombok.NoArgsConstructor;

public class NoSuchDataException extends RuntimeException {
    public NoSuchDataException(String message) {
        super(message);
    }
}
