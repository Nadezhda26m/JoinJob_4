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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Репозиторий для работы с данными о животных
 */
public class AnimalCollectionRepository implements AnimalRepository {

    /**
     * Список всех животных, сохраненных в репозитории
     */
    private final List<Animal> animals;

    /**
     * Уникальный идентификатор животного
     */
    private final AtomicLong id;

    /**
     * Конструктор для инициализации полей и добавления начальных данных о животных
     */
    public AnimalCollectionRepository() {
        this.animals = new ArrayList<>();
        this.id = new AtomicLong();
        init();
    }

    /**
     * Получение списка всех животных.
     * @return список всех животных
     */
    @Override
    public List<Animal> findAll() {
        return animals;
    }

    /**
     * Получение списка животных определенного класса-наследника Animal.
     * @param type класс-наследник Animal
     * @return список животных указанного вида
     * @param <E> класс-наследник Animal
     */
    @Override
    public <E extends Animal> List<Animal> findByType(Class<E> type) {
        // List<Animal> list = new ArrayList<>();
        // for (var animal : animals) {
        //     if (type.isAssignableFrom(animal.getClass())) {
        //         list.add(animal); } }
        // return list;
        return animals.stream()
                .filter(type::isInstance)
                .toList();
    }

    /**
     * Сохранение или обновление сущности в репозитории.
     * @param entity сущность для сохранения или обновления
     * @return сохраненная сущность
     */
    @Override
    public Animal save(Animal entity) {
        int index = findIndexById(entity.getId());
        if (index == -1) {
            entity.setId(id.incrementAndGet());
            animals.add(entity);
        } else {
            animals.set(index, entity);
        }
        return entity;
    }

    /**
     * Поиск сущности с указанным ID.
     * @param id уникальный идентификатор сущности
     * @return Optional объект сущности с указанным ID
     */
    @Override
    public Optional<Animal> findById(Long id) {
        if (id == null) return Optional.empty();
        return animals.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst();
    }

    /**
     * Поиск индекса сущности с указанным ID в списке животных
     * @param id уникальный идентификатор животного
     * @return индекс сущности в списке животных или -1, если такого ID не найдено
     */
    private int findIndexById(Long id) {
        if (id != null) {
            for (int i = 0; i < animals.size(); i++) {
                if (animals.get(i).getId().equals(id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Задание начального списка животных
     */
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
