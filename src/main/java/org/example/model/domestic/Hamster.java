package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Сущность хомяка
 */
public class Hamster extends DomesticAnimal {

    /**
     * Конструктор для задания вида животного (Хомяк), клички, даты рождения и списка команд.
     * @param name кличка животного
     * @param birthday дата рождения животного
     */
    public Hamster(String name, LocalDate birthday) {
        super("Хомяк", name, birthday, new ArrayList<>());
    }

}
