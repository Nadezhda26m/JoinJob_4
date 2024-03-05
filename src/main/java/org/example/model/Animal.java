package org.example.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Животные
 */
public abstract class Animal {

    protected Long id;

    protected String animalGroup;
    protected String animalType;

    protected String name;
    protected LocalDate birthday;
    protected List<Command> commands;

    public Animal(String animalGroup, String animalType, String name, LocalDate birthday) {
        this.animalGroup = animalGroup;
        this.animalType = animalType;
        this.name = name;
        this.birthday = birthday;
        this.commands = new ArrayList<>();
    }

    @Override
    public abstract String toString();
}
