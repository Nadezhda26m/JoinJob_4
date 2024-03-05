package org.example.controller;

import org.example.repository.AnimalRepository;
import org.example.service.AnimalService;
import org.example.service.Counter;
import org.example.view.View;

/**
 * Контроллер
 */
public class AnimalController {

    /*
    Завести новое животное
    определять животное в правильный класс
    увидеть список команд, которое выполняет животное
    обучить животное новым командам
    Реализовать навигацию по меню
     */

    private final View view;

    private final AnimalService service;

    private final Counter counter;

    public AnimalController(View view, AnimalRepository repository) {
        this.view = view;
        this.service = new AnimalService(repository);
        this.counter = new Counter();
    }

    public void run() {
        view.print("AnimalController run");


    }
}
