package org.example.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Вьючные животные
 */
public abstract class PackAnimal extends Animal {

    /**
     * Конструктор для задания группы животного (Вьючные животные).
     * @param animalType вид животного
     * @param name кличка животного
     * @param birthday дата рождения животного
     * @param commands список команд
     */
    public PackAnimal(String animalType, String name,
                      LocalDate birthday, List<Command> commands) {
        super("Вьючные животные", animalType, name, birthday, commands);
    }

}
