package org.example.controller;

import org.example.exception.IncorrectDataException;
import org.example.model.Animal;
import org.example.model.baggage.Camel;
import org.example.model.baggage.Donkey;
import org.example.model.baggage.Horse;
import org.example.model.domestic.Cat;
import org.example.model.domestic.Dog;
import org.example.model.domestic.Hamster;
import org.example.repository.AnimalRepository;
import org.example.service.AnimalService;
import org.example.service.Counter;
import org.example.view.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Контроллер
 */
public class AnimalController {

    private final View view;

    private final AnimalService service;

    private List<String> menu;

    private List<String> animalTypes;

    public AnimalController(View view, AnimalRepository repository) {
        this.view = view;
        this.service = new AnimalService(repository);
        this.menu = new ArrayList<>();
        this.animalTypes = new ArrayList<>();
        createMenuList();
        createAnimalTypesList();
    }

    public void run() {
        view.println("Добро пожаловать в приложение нашего питомника!");
        int item;
        try (Counter counter = new Counter()) {
            do {
                view.println("\nВыберите пункт меню");
                view.printNumberedList(menu, 0);
                item = selectMenuItem(0, menu.size() - 1);
                switch (item) {
                    case 1 -> showAllAnimals();
                    case 2 -> showAnimalsByType();
                    case 3 -> addNewAnimal(counter);
                }
            } while (item != 0);
        } catch (IOException e) {
            view.println(e.getMessage());
        }
        view.println("\nДо новых встреч!");
    }

    private void createMenuList() {
        menu.add("Завершить работу и выйти");
        menu.add("Посмотреть список всех животных питомника");
        menu.add("Посмотреть список животных питомника определенного вида");
        menu.add("Добавить в питомник новое животное");
    }

    private void createAnimalTypesList() {
        animalTypes.addAll(Arrays.asList(
                "Кошка", "Собака", "Хомяк", "Лошадь", "Осёл", "Верблюд"));
    }

    private void showAllAnimals() {
        view.println("<<< Список животных питомника >>>");
        view.printList(service.getAllAnimals());
    }

    private <T extends Animal> void showAnimalsWithType(Class<T> type, String typeName) {
        view.println("<<< Список животных вида " + typeName + " >>>");
        List<T> animalsByType = service.getAnimalsByType(type);
        if (animalsByType.isEmpty()) {
            view.println("Никого не нашли...");
        } else {
            view.printList(animalsByType);
        }
    }

    private int selectMenuItem(int minValue, int maxValue) {
        view.print("Введите число: ");
        try {
            return view.getInt(minValue, maxValue);
        } catch (IncorrectDataException e) {
            view.println("Ошибка ввода: " + e.getMessage());
            return selectMenuItem(minValue, maxValue);
        }
    }

    private int selectAnimalType() {
        view.println("Выберите вид животного");
        view.printNumberedList(animalTypes);
        return selectMenuItem(1, animalTypes.size());
    }

    private void showAnimalsByType() {
        int typeId = selectAnimalType() - 1;
        switch (typeId) {
            case 0 -> showAnimalsWithType(Cat.class, animalTypes.get(typeId));
            case 1 -> showAnimalsWithType(Dog.class, animalTypes.get(typeId));
            case 2 -> showAnimalsWithType(Hamster.class, animalTypes.get(typeId));
            case 3 -> showAnimalsWithType(Horse.class, animalTypes.get(typeId));
            case 4 -> showAnimalsWithType(Donkey.class, animalTypes.get(typeId));
            case 5 -> showAnimalsWithType(Camel.class, animalTypes.get(typeId));
            default -> view.println("Не знаю такой вид");
        }
    }

    private void addNewAnimal(Counter counter) {
        int typeId = selectAnimalType();
        String name = getAnimalName();
        LocalDate birthday = getAnimalBirthday();
        Animal animal = null;
        try {
            switch (typeId) {
                case 1 -> animal = service.addNewAnimal(Cat.class, name, birthday);
                case 2 -> animal = service.addNewAnimal(Dog.class, name, birthday);
                case 3 -> animal = service.addNewAnimal(Hamster.class, name, birthday);
                case 4 -> animal = service.addNewAnimal(Horse.class, name, birthday);
                case 5 -> animal = service.addNewAnimal(Donkey.class, name, birthday);
                case 6 -> animal = service.addNewAnimal(Camel.class, name, birthday);
                default -> view.println("Не знаю такой вид");
            }
            if (animal != null) {
                view.println("Добавлено животное: ");
                view.println(animal.toString());
                counter.add();
            }
        } catch (Exception e) {
            view.println(e.getMessage());
        }
    }

    private String getAnimalName() {
        view.print("Введите имя животного: ");
        String name = view.getMessage();
        try {
            service.isCorrectName(name);
            return name;
        } catch (RuntimeException e) {
            view.println("Ошибка ввода: " + e.getMessage());
            return getAnimalName();
        }
    }

    private LocalDate getAnimalBirthday() {
        view.print("Введите дату рождения животного в формате гггг-мм-дд: ");
        String birthday = view.getMessage().trim();
        try {
            LocalDate date = LocalDate.parse(birthday);
            if (date.isAfter(LocalDate.now())
                    || date.isBefore(LocalDate.now().minusYears(50))) {
                view.println("Ошибка ввода: укажите корректную дату");
                getAnimalBirthday();
            }
            return date;
        } catch (DateTimeParseException e) {
            view.println("Ошибка ввода: неверный формат даты");
            return getAnimalBirthday();
        }
    }

}
