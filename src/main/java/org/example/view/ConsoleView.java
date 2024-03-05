package org.example.view;

import java.util.List;

public class ConsoleView implements View {

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void printList(List<?> list) {
        for (var o : list) {
            System.out.println(o);
        }
    }
}
