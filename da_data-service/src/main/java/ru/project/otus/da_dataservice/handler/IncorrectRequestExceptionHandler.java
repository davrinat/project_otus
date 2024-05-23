package ru.project.otus.da_dataservice.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.project.otus.da_dataservice.exception.IncorrectRequestException;
import ru.project.otus.da_dataservice.model.error.Error;

@ControllerAdvice
@Slf4j
public class IncorrectRequestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Error> catchResourceNotFoundException(IncorrectRequestException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new Error(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
