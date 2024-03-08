package org.example.model.baggage;

import org.example.model.PackAnimal;

import java.time.LocalDate;

/**
 * Сущность осла
 */
public class Donkey extends PackAnimal {

    public Donkey(String name, LocalDate birthday) {
        super("Осёл", name, birthday);
    }

}
