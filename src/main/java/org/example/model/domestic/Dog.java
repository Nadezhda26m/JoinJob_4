package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Сущность собаки
 */
public class Dog extends DomesticAnimal {

    /**
     * Конструктор для задания вида животного (Собака), клички, даты рождения и списка команд.
     * @param name кличка животного
     * @param birthday дата рождения животного
     */
    public Dog(String name, LocalDate birthday) {
        super("Собака", name, birthday, new ArrayList<>());
    }

}
