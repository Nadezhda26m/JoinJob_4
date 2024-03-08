package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;

/**
 * Сущность собаки
 */
public class Dog extends DomesticAnimal {

    public Dog(String name, LocalDate birthday) {
        super("Собака", name, birthday);
    }

}
