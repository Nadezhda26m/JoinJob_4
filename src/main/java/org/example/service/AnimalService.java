package org.example.service;

import org.example.exception.IncorrectDataException;
import org.example.model.Animal;
import org.example.repository.AnimalRepository;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public <T extends Animal> T addNewAnimal(
            Class<T> animalType, String name, LocalDate birthday
    )
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        var animal = animalType
                .getDeclaredConstructor(String.class, LocalDate.class)
                .newInstance(getCorrectName(name), birthday);
        return repository.save(animal);
    }

    private String getCorrectName(String name) {
        String s = name.trim().toLowerCase();
        StringBuilder sb = new StringBuilder();

        if (s.contains(" ") || s.contains("-")) {
            String[] names = s.split("[\s\\-]");
            sb.append(Character.toUpperCase(names[0].charAt(0)));
            sb.append(names[0].substring(1));
            sb.append(name.contains("-") ? '-' : ' ');
            sb.append(Character.toUpperCase(names[1].charAt(0)));
            sb.append(names[1].substring(1));
        } else {
            sb.append(Character.toUpperCase(s.charAt(0)));
            sb.append(s.substring(1));
        }
        return sb.toString();
    }

    public boolean isCorrectName(String name) {
        if (name == null || name.isEmpty())
            throw new NullPointerException("Имя не задано");
        if (name.trim().length() > 25) {
            throw new IncorrectDataException(
                    "Слишком длинное имя");
        }
        // начинается с 2 и более а-я, 1 или 0 раз через пробел или тире еще одно слово не менее 2 символов
        // Мурзик, или Микки Маус, или Бер-Бера
        if (!name.trim().matches("[А-ЯЁа-яё]{2,}([\s\\-][А-ЯЁа-яё]{2,})?"))
            throw new IncorrectDataException(
                    "Некорректные данные. Нельзя задать имя '" + name + '\'');
        return true;
    }


    public List<Animal> getAllAnimals() {
        return repository.findAll();
    }

    public <T extends Animal> List<T> getAnimalsByType(Class<T> type) {
        return repository.findByType(type);
    }


}
