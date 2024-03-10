package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Сущность верблюда
 */
public class Camel extends PackAnimal {

    /**
     * Конструктор для задания вида животного (Верблюд), клички, даты рождения и списка команд.
     * @param name кличка животного
     * @param birthday дата рождения животного
     */
    public Camel(String name, LocalDate birthday) {
        super("Верблюд", name, birthday, new ArrayList<>());
    }

}
