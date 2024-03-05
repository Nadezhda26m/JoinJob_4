package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;

/**
 * Сущность кошки
 */
public class Cat extends DomesticAnimal {

    public Cat(String name, LocalDate birthday) {
        super("Кошки", name, birthday);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "animalGroup='" + animalGroup + '\'' +
                ", animalType='" + animalType + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", commands=" + commands +
                '}';
    }
}
