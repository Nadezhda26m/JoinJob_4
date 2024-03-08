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
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", группа: ").append(animalGroup);
        sb.append(", вид: ").append(animalType);
        sb.append(", кличка: ").append(name);
        sb.append(", дата рождения: ").append(birthday);
        sb.append(", список команд: ").append(commands);
        return sb.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }
}
