package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;

/**
 * Сущность осла
 */
public class Donkey extends PackAnimal {

    public Donkey(String name, LocalDate birthday) {
        super("Ослы", name, birthday);
    }

    @Override
    public String toString() {
        return "Donkey{" +
                "animalGroup='" + animalGroup + '\'' +
                ", animalType='" + animalType + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", commands=" + commands +
                '}';
    }
}
