package org.example.model;

import java.time.LocalDate;

/**
 * Домашние животные
 */
public abstract class DomesticAnimal extends Animal {

    public DomesticAnimal(String animalType, String name, LocalDate birthday) {
        super("Домашние животные", animalType, name, birthday);
    }

}
