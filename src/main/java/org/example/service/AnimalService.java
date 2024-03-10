package org.example.service;

import org.example.exception.IncorrectDataException;
import org.example.model.Animal;
import org.example.model.Command;
import org.example.model.baggage.Camel;
import org.example.model.baggage.Donkey;
import org.example.model.baggage.Horse;
import org.example.model.domestic.Cat;
import org.example.model.domestic.Dog;
import org.example.model.domestic.Hamster;
import org.example.repository.AnimalRepository;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис для работы с данными о животных
 */
public class AnimalService {

    /**
     * Интерфейс репозитория для работы с данными о животных
     */
    private final AnimalRepository repository;

    /**
     * Сервис для работы с командами
     */
    private final CommandsService commandsService;

    /**
     * Объект для связи классов-наследников Animal (Value) и их строкового представления (Key)
     */
    private final Map<String, Class<? extends Animal>> animalTypes;

    /**
     * Конструктор класса для подключения к конкретному классу репозитория.
     * Подключение к сервису команд и добавление списка классов-наследников Animal.
     * @param repository класс репозитория, реализующий интерфейс AnimalRepository
     */
    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
        this.commandsService = new CommandsService();
        this.animalTypes = new HashMap<>();
        specifyAnimalTypes();
    }

    /**
     * Задание пар для связи классов-наследников Animal (Value) и их строкового представления (Key)
     */
    private void specifyAnimalTypes() {
        animalTypes.put("Кошка", Cat.class);
        animalTypes.put("Собака", Dog.class);
        animalTypes.put("Хомяк", Hamster.class);
        animalTypes.put("Лошадь", Horse.class);
        animalTypes.put("Осёл", Donkey.class);
        animalTypes.put("Верблюд", Camel.class);
    }

    /**
     * Создание животного указанного вида, задание клички и даты рождения животного,
     * сохранение созданного животного в репозитории.
     * @param type вид животного (значение ключа в словаре классов-наследников)
     * @param name кличка животного
     * @param birthday дата рождения животного
     * @return сохраненная сущность животного
     * @throws NoSuchMethodException не найден указанный конструктор
     * @throws InvocationTargetException исключение, которое вызывает указанный конструктор
     * @throws InstantiationException вызов конструктора абстрактного класса
     * @throws IllegalAccessException отсутствие доступа к базовому конструктору
     */
    public Animal addNewAnimal(String type, String name, LocalDate birthday)
            throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Animal animal = animalTypes.get(type)
                .getDeclaredConstructor(String.class, LocalDate.class)
                .newInstance(getCorrectName(name), birthday);
        return repository.save(animal);
    }

    /**
     * Обновление информации о животном в репозитории, если животное с указанным ID существует.
     * В ином случае будет добавлена новая запись с присвоеннием ID.
     * @param animal объект животного для добавления или обновления
     * @return сохраненная сущность животного
     */
    public Animal addOrUpdateAnimal(Animal animal) {
        return repository.save(animal);
    }

    /**
     * Проверка клички животного на корректность. Кличка не должна быть короче 2
     * и длинее 30 символов. Допускается одно слово или два слова, разделенных пробелом
     * или тире, записанные буквами русского алфавита в любом регистре.
     * Может вызывать исключения.
     * @param name кличка животного для проверки
     * @return результат проверки на коректность
     */
    public boolean isCorrectName(String name) {
        if (name == null || name.isEmpty())
            throw new IncorrectDataException("Имя не задано");
        if (name.trim().length() > 30)
            throw new IncorrectDataException("Слишком длинное имя");
        // Мурзик, или Микки Маус, или Бер-Бера
        if (!name.trim().matches("[А-ЯЁа-яё]{2,}([\s\\-][А-ЯЁа-яё]{2,})?"))
            throw new IncorrectDataException(
                    "Некорректные данные. Нельзя задать имя '" + name + '\'');
        return true;
    }

    /**
     * Получение отсортированного списка всех доступных команд.
     * @return список доступных команд
     */
    public List<Command> getAllCommands() {
        return commandsService.getSortedList();
    }

    /**
     * Создание новой команды. Выдаст исключение, если переданное название некорректно
     * или команда с указанным названием уже существует.
     * @param command название команды для добавления
     * @return объект созданной команды
     */
    public Command createNewCommand(String command) {
        return commandsService.createNewCommand(command);
    }

    /**
     * Получение списка всех животных из репозитория.
     * @return список животных
     */
    public List<Animal> getAllAnimals() {
        return repository.findAll();
    }

    /**
     * Получение списка животных определенного класса-наследника Animal.
     * @param type вид животного (значение ключа в словаре классов-наследников)
     * @return список животных указанного вида
     */
    public List<Animal> getAnimalsByType(String type) {
        return repository.findByType(animalTypes.get(type));
    }

    /**
     * Получение корректной записи клички животного, где первая буква каждого слова
     * заглавная, а остальные строчные.
     * @param name кличка животного
     * @return корректная запись клички животного
     */
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

    /**
     * Получение списка ключей-названий доступных классов-наследников (видов) животных.
     * @return список доступных видов животных
     */
    public List<String> getAnimalTypes() {
        return animalTypes.keySet().stream().toList();
    }

    /**
     * Получение животного с указанным ID
     * @param id уникальный идентификатор животного
     * @return объект найденного животного или null
     */
    public Animal getAnimalById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
