package org.example.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Модель команды
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Command implements Comparable<Command> {

    /**
     * Название команды
     */
    private String name;

    /**
     * Вывод названия команды.
     * @return название команды
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * Метод сравнения двух команд по их названию.
     * @param o соманда для сравнения с текущей
     * @return результат сравнения
     */
    @Override
    public int compareTo(Command o) {
        return this.name.compareTo(o.name);
    }
}
