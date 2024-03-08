package org.example.view;

import org.example.exception.IncorrectDataException;

import java.util.List;
import java.util.Scanner;

public class ConsoleView implements View {

    private final Scanner scan = new Scanner(System.in);

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void printList(List<?> list) {
        for (var o : list) {
            println(o.toString());
        }
    }

    @Override
    public void printNumberedList(List<?> list, int startRawNumber) {
        for (var o : list) {
            System.out.printf("%d. %s\n", startRawNumber++, o.toString());
        }
    }

    @Override
    public void printNumberedList(List<?> list) {
        this.printNumberedList(list, 1);
    }

    @Override
    public String getMessage() {
        return scan.nextLine();
    }

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

    @Override
    public int getInt(int minValue, int maxValue) {
        int number = getInt();
        if (number < minValue || number > maxValue) {
            throw new IncorrectDataException("Число " + number + " вне диапазона");
        }
        return number;
    }

}
