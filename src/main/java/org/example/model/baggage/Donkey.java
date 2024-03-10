package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Сущность осла
 */
public class Donkey extends PackAnimal {

    /**
     * Конструктор для задания вида животного (Осёл), клички, даты рождения и списка команд.
     * @param name кличка животного
     * @param birthday дата рождения животного
     */
    public Donkey(String name, LocalDate birthday) {
        super("Осёл", name, birthday, new ArrayList<>());
    }

}
