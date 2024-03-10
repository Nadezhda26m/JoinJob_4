package org.example.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Домашние животные
 */
public abstract class DomesticAnimal extends Animal {

    /**
     * Конструктор для задания группы животного (Домашние животные).
     * @param animalType вид животного
     * @param name кличка животного
     * @param birthday дата рождения животного
     * @param commands список команд
     */
    public DomesticAnimal(String animalType, String name,
                          LocalDate birthday, List<Command> commands) {
        super("Домашние животные", animalType, name, birthday, commands);
    }

}
