package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;

/**
 * Сущность лошади
 */
public class Horse extends PackAnimal {


    public Horse(String name, LocalDate birthday) {
        super("Лошади", name, birthday);
    }

    @Override
    public String toString() {
        return "Horse{" +
                "animalGroup='" + animalGroup + '\'' +
                ", animalType='" + animalType + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", commands=" + commands +
                '}';
    }
}
