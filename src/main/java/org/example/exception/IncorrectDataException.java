package org.example.exception;

/**
 * Исключение, которое выбрасывается во время выполнения программы при получении некорректных данных
 */
public class IncorrectDataException extends RuntimeException {

    /**
     * Исключение, которое выбрасывается во время выполнения программы при получении
     * некорректных данных.
     * @param message сообщение об ошибке
     */
    public IncorrectDataException(String message) {
        super(message);
    }

    /**
     * Исключение, которое выбрасывается во время выполнения программы при получении
     * некорректных данных. Содержит сообщение об ошибке: Некорректные данные.
     */
    public IncorrectDataException() {
        super("Некорректные данные");
    }

    /**
     * Исключение, которое выбрасывается во время выполнения программы при получении
     * некорректных данных.
     * @param message сообщение об ошибке
     * @param cause исключение, которое было причиной возникновения данного исключения
     */
    public IncorrectDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
