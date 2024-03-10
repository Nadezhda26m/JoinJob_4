package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Сущность кошки
 */
public class Cat extends DomesticAnimal {

    /**
     * Конструктор для задания вида животного (Кошка), клички, даты рождения и списка команд.
     * @param name кличка животного
     * @param birthday дата рождения животного
     */
    public Cat(String name, LocalDate birthday) {
        super("Кошка", name, birthday, new ArrayList<>());
    }

}
