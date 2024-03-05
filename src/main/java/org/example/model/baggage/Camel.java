package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;

/**
 * Сущность верблюда
 */
public class Camel extends PackAnimal {

    public Camel(String name, LocalDate birthday) {
        super("Верблюды", name, birthday);
    }

    @Override
    public String toString() {
        return "Camel{" +
                "animalGroup='" + animalGroup + '\'' +
                ", animalType='" + animalType + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", commands=" + commands +
                '}';
    }
}
