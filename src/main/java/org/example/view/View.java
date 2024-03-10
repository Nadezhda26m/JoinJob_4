package org.example.view;

import java.util.List;

/**
 * Интерфейс View
 */
public interface View {

    /**
     * Вывод сообщения без перехода на новую строку.
     * @param text сообщение для отображения
     */
    void print(String text);

    /**
     * Вывод сообщения с переходом на новую строку.
     * @param text сообщение для отображения
     */
    void println(String text);

    /**
     * Построчный вывод элементов списка.
     * @param list список для отображения
     */
    void printList(List<?> list);

    /**
     * Построчный вывод элементов списка с возрастающей нумерацией каждой строки,
     * начиная с указанного числа.
     * @param list список для отображения
     * @param startRawNumber начальный номер первой строки
     */
    void printNumberedList(List<?> list, int startRawNumber);

    /**
     * Построчный вывод элементов списка с возрастающей нумерацией каждой строки, начиная с 1.
     * @param list список для отображения
     */
    void printNumberedList(List<?> list);

    /**
     * Получение введенной строки.
     * @return полученная строка
     */
    String getMessage();

    /**
     * Ввод числа типа int. Может вызывать исключения.
     * @return число
     */
    int getInt();

    /**
     * Ввод числа типа int в указанном диапазоне. Может вызывать исключения.
     * @param minValue минимальное значение числа, включено
     * @param maxValue максимальное значение числа, включено
     * @return число
     */
    int getInt(int minValue, int maxValue);

    /**
     * Ввод числа типа long. Может вызывать исключения.
     * @return число
     */
    long getLong();

    /**
     * Ввод числа типа long в указанном диапазоне. Может вызывать исключения.
     * @param minValue минимальное значение числа, включено
     * @param maxValue максимальное значение числа, включено
     * @return число
     */
    long getLong(long minValue, long maxValue);
}
