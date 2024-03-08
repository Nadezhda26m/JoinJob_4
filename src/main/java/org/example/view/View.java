package org.example.view;

import java.util.List;

public interface View {

    void print(String text);

    void println(String text);

    void printList(List<?> list);

    void printNumberedList(List<?> list, int startRawNumber);

    void printNumberedList(List<?> list);

    String getMessage();

    int getInt();

    int getInt(int minValue, int maxValue);
}
