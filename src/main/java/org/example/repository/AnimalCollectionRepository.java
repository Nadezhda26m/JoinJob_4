package org.example.repository;

import org.example.model.Animal;

import java.util.List;

/**
 * Репозиторий для работы с данными о животных
 */
public class AnimalCollectionRepository implements AnimalRepository {

    @Override
    public List<Animal> findAll() {
        return null;
    }

    @Override
    public <E extends Animal> E save(E entity) {
        return null;
    }
}
