package org.example.model;

import java.time.LocalDate;

/**
 * Вьючные животные
 */
public abstract class PackAnimal extends Animal {

    public PackAnimal(String animalType, String name, LocalDate birthday) {
        super("Вьючные животные", animalType, name, birthday);
    }


}
