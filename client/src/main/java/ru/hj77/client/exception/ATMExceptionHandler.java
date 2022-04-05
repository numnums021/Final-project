package ru.hj77.client.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.hj77.common.exception.IncorrectData;
import ru.hj77.common.exception.NoSuchDataException;

@ControllerAdvice
public class ATMExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            NoSuchDataException exception){

        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            Exception exception){

        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
