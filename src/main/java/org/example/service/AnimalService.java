package org.example.service;

import org.example.model.Animal;
import org.example.repository.AnimalRepository;

public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public <T extends Animal> T addNewAnimal(T animal) {
        return repository.save(animal);
    }
}
