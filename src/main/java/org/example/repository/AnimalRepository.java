package org.example.repository;

import org.example.model.Animal;

import java.util.List;


public interface AnimalRepository extends Repository<Animal, Long> {

    <T extends Animal> List<T> findByType(Class<T> type);
}
