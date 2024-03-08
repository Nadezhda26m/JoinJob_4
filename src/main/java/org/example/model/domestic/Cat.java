package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;

/**
 * Сущность кошки
 */
public class Cat extends DomesticAnimal {

    public Cat(String name, LocalDate birthday) {
        super("Кошка", name, birthday);
    }

}
