package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Сущность лошади
 */
public class Horse extends PackAnimal {

    /**
     * Конструктор для задания вида животного (Лошадь), клички, даты рождения и списка команд.
     * @param name кличка животного
     * @param birthday дата рождения животного
     */
    public Horse(String name, LocalDate birthday) {
        super("Лошадь", name, birthday, new ArrayList<>());
    }

}
