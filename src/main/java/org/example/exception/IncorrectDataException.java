package org.example.exception;

public class IncorrectDataException extends RuntimeException {

    public IncorrectDataException(String message) {
        super(message);
    }

    public IncorrectDataException() {
        super("Некорректные данные");
    }
}