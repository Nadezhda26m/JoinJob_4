package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;

/**
 * Сущность собаки
 */
public class Dog extends DomesticAnimal {

    public Dog(String name, LocalDate birthday) {
        super("Собаки", name, birthday);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "animalGroup='" + animalGroup + '\'' +
                ", animalType='" + animalType + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", commands=" + commands +
                '}';
    }
}
