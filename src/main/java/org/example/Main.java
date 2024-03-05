package org.example;

import org.example.controller.AnimalController;
import org.example.repository.AnimalCollectionRepository;
import org.example.view.ConsoleView;

public class Main {
    public static void main(String[] args) {

        AnimalController controller = new AnimalController(
                new ConsoleView(), new AnimalCollectionRepository());

        controller.run();
    }
}