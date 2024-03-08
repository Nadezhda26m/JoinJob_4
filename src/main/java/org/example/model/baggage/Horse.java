package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;

/**
 * Сущность лошади
 */
public class Horse extends PackAnimal {

    public Horse(String name, LocalDate birthday) {
        super("Лошадь", name, birthday);
    }

}
