package org.example.repository;

import org.example.model.Animal;
import org.example.model.baggage.Camel;
import org.example.model.baggage.Donkey;
import org.example.model.baggage.Horse;
import org.example.model.domestic.Cat;
import org.example.model.domestic.Dog;
import org.example.model.domestic.Hamster;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Репозиторий для работы с данными о животных
 */
public class AnimalCollectionRepository implements AnimalRepository {

    private final List<Animal> animals;

    private final AtomicLong id;

    public AnimalCollectionRepository() {
        this.animals = new ArrayList<>();
        this.id = new AtomicLong();
        init();
    }

    @Override
    public List<Animal> findAll() {
        return animals;
    }

    @Override
    public <T extends Animal> List<T> findByType(Class<T> type) {
        // List<T> list = new ArrayList<>();
        // for (var animal : animals) {
        //     if (type.isAssignableFrom(animal.getClass())) {
        //         list.add((T) animal); } }
        // return list;

        return animals.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .toList();
    }

    @Override
    public <E extends Animal> E save(E entity) {
        entity.setId(id.incrementAndGet());
        animals.add(entity);
        return entity;
    }

    private void init() {
        save(new Dog("Шарик",
                LocalDate.now().minusYears(1).minusMonths(2)));
        save(new Dog("Бобик",
                LocalDate.now().minusYears(6).minusMonths(1)));
        save(new Dog("Пират",
                LocalDate.parse("2019-03-29")));

        save(new Cat("Мурзик",
                LocalDate.now().minusYears(8).minusMonths(3)));
        save(new Cat("Тузик",
                LocalDate.now().minusYears(2).minusMonths(5)));
        save(new Cat("Мальвина",
                LocalDate.now().minusYears(1).minusMonths(9)));

        save(new Hamster("Грызлик",
                LocalDate.now().minusMonths(5)));
        save(new Hamster("Хома",
                LocalDate.now().minusYears(1).minusMonths(9)));
        save(new Hamster("Пухлик",
                LocalDate.now().minusDays(94)));

        save(new Horse("Ветер",
                LocalDate.now().minusYears(18)));
        save(new Horse("Стрела",
                LocalDate.now().minusYears(2).minusMonths(11)));
        save(new Horse("Джеки",
                LocalDate.now().minusYears(11).minusMonths(5)));

        save(new Camel("Верблюд1",
                LocalDate.parse("2015-03-29")));
        save(new Camel("Верблюд2",
                LocalDate.parse("2015-03-29")));
        save(new Camel("Верблюд3",
                LocalDate.parse("2015-03-29")));

        save(new Donkey("Иаа",
                LocalDate.now().minusMonths(135)));
        save(new Donkey("Осел2",
                LocalDate.now().minusYears(24)));
        save(new Donkey("Ослик3",
                LocalDate.now().minusYears(10).minusMonths(2)));
    }

}
