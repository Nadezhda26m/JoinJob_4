package org.example.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

/**
 * Родительский класс всех животных
 */
@EqualsAndHashCode
@Getter
public abstract class Animal {

    /**
     * Уникальный идентификатор животного
     */
    protected Long id;

    /**
     * Группа животного
     */
    protected String animalGroup;

    /**
     * Вид животного
     */
    protected String animalType;

    /**
     * Кличка животного
     */
    protected String name;

    /**
     * Дата рождения животного
     */
    protected LocalDate birthday;

    /**
     * Список команд, которым обучено животное
     */
    protected List<Command> commands;

    /**
     * Конструктор класса.
     * @param animalGroup группа животного
     * @param animalType вид животного
     * @param name кличка животного
     * @param birthday дата рождения животного
     * @param commands список команд
     */
    public Animal(String animalGroup, String animalType, String name,
                  LocalDate birthday, List<Command> commands) {
        this.animalGroup = animalGroup;
        this.animalType = animalType;
        this.name = name;
        this.birthday = birthday;
        this.commands = commands;
    }

    /**
     * Получение строки с полной информацией о животном.
     * @return строковое представление сущности животного
     */
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

    /**
     * Получение строки с информацией о ID, виде и кличке животного.
     * @return строка с краткой информацией о животном
     */
    public String getShortInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("id=").append(id);
        sb.append(", вид: ").append(animalType);
        sb.append(", кличка: ").append(name);
        return sb.toString();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }
}
