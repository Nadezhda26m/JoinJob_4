package org.example.repository;

import org.example.model.Animal;

import java.util.List;

/**
 * Интерфейс репозитория для работы с данными о животных
 */
public interface AnimalRepository extends Repository<Animal, Long> {

    /**
     * Получение списка животных определенного класса-наследника Animal.
     * @param type класс-наследник Animal
     * @return список животных указанного класса
     * @param <E> класс-наследник Animal
     */
    <E extends Animal> List<Animal> findByType(Class<E> type);

}
