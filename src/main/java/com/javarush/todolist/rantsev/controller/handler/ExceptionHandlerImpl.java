package com.javarush.todolist.rantsev.controller.handler;

import com.javarush.todolist.rantsev.exception.TaskNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Task not found")
    public void handleTaskNotFoundException(TaskNotFoundException e){
        log.error(e.getMessage());
    }
}
