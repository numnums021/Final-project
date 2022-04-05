package ru.hj77.common.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchDataException extends RuntimeException {

    public NoSuchDataException(String message) {
        super(message);
    }
}
