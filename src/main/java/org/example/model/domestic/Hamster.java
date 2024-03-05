package org.example.model.domestic;

import org.example.model.DomesticAnimal;

import java.time.LocalDate;

/**
 * Сущность хомяка
 */
public class Hamster extends DomesticAnimal {

    public Hamster(String name, LocalDate birthday) {
        super("Хомяки", name, birthday);
    }


    @Override
    public String toString() {
        return "Hamster{" +
                "animalGroup='" + animalGroup + '\'' +
                ", animalType='" + animalType + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", commands=" + commands +
                '}';
    }
}
