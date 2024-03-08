package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;

/**
 * Сущность хомяка
 */
public class Hamster extends DomesticAnimal {

    public Hamster(String name, LocalDate birthday) {
        super("Хомяк", name, birthday);
    }

}
