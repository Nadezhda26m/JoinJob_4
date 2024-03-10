package org.example.view;

import org.example.exception.IncorrectDataException;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

/**
 * Ввод и вывод данных через консоль
 */
public class ConsoleView implements View {

    /**
     * Сканер для получения данных из консоли.
     */
    private final Scanner scan = new Scanner(System.in);

    /**
     * Экземпляр PrintWriter для обработки текста и вывода сообщений в консоль
     */
    private final PrintWriter out = new PrintWriter(System.out, false, StandardCharsets.UTF_8);

    /**
     * Вывод сообщения в консоль без перехода на новую строку.
     * @param text сообщение для отображения
     */
    @Override
    public void print(String text) {
        out.print(text);
        out.flush();
    }

    /**
     * Вывод сообщения в консоль с переходом на новую строку.
     * @param text сообщение для отображения
     */
    @Override
    public void println(String text) {
        out.println(text);
        out.flush();
    }

    /**
     * Построчный вывод элементов списка в консоль.
     * @param list список для отображения
     */
    @Override
    public void printList(List<?> list) {
        StringBuilder sb = new StringBuilder();
        for (var o : list) {
            sb.append(o.toString()).append('\n');
        }
        print(sb.toString());
    }

    /**
     * Построчный вывод элементов списка в консоль с возрастающей нумерацией каждой строки,
     * начиная с указанного числа.
     * @param list список для отображения
     * @param startRawNumber начальный номер первой строки
     */
    @Override
    public void printNumberedList(List<?> list, int startRawNumber) {
        StringBuilder sb = new StringBuilder();
        for (var o : list) {
            sb.append(startRawNumber++).append(". ").append(o.toString()).append('\n');
        }
        print(sb.toString());
    }

    /**
     * Построчный вывод элементов списка в консоль с возрастающей нумерацией каждой строки,
     * начиная с 1.
     * @param list список для отображения
     */
    @Override
    public void printNumberedList(List<?> list) {
        this.printNumberedList(list, 1);
    }

    /**
     * Получение введенной в консоли строки.
     * @return полученная строка
     */
    @Override
    public String getMessage() {
        return scan.nextLine();
    }

    /**
     * Получение из консоли числа типа int. Может вызвать исключение IncorrectDataException.
     * @return полученное число
     */
    @Override
    public int getInt() {
        String value = "";
        try {
            value = scan.nextLine();
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(
                    "Некорректные данные. Введенное значение '" + value + "' не является числом", e);
        }
    }

    /**
     * Получение из консоли числа типа int в указанном диапазоне.
     * Может вызвать исключение IncorrectDataException.
     * @param minValue минимальное значение числа, включено
     * @param maxValue максимальное значение числа, включено
     * @return полученное число
     */
    @Override
    public int getInt(int minValue, int maxValue) {
        int number = getInt();
        if (number < minValue || number > maxValue) {
            throw new IncorrectDataException("Число " + number + " вне диапазона");
        }
        return number;
    }

    /**
     * Получение из консоли числа типа long. Может вызвать исключение IncorrectDataException.
     * @return полученное число
     */
    @Override
    public long getLong() {
        String value = "";
        try {
            value = scan.nextLine();
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(
                    "Некорректные данные. Введенное значение '" + value + "' не является числом", e);
        }
    }

    /**
     * Получение из консоли числа типа long в указанном диапазоне.
     * Может вызвать исключение IncorrectDataException.
     * @param minValue минимальное значение числа, включено
     * @param maxValue максимальное значение числа, включено
     * @return полученное число
     */
    @Override
    public long getLong(long minValue, long maxValue) {
        long number = getLong();
        if (number < minValue || number > maxValue) {
            throw new IncorrectDataException("Число " + number + " вне диапазона");
        }
        return number;
    }

}
