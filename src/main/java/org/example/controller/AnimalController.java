package org.example.controller;

import org.example.exception.IncorrectDataException;
import org.example.model.Animal;
import org.example.model.Command;
import org.example.repository.AnimalRepository;
import org.example.service.AnimalService;
import org.example.service.Counter;
import org.example.view.View;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер приложения, предоставляет меню для работы с данными о животных
 */
public class AnimalController {

    /**
     * Интерфейс View для ввода и вывода данных
     */
    private final View view;

    /**
     * Сервис для работы с данными о животных
     */
    private final AnimalService service;

    /**
     * Список доступных пунктов меню приложения
     */
    private final List<String> menu;

    /**
     * Доступные виды животных
     */
    private final List<String> animalTypes;

    /**
     * Конструктор для задания способа взаимодействия с пользователем и способа хранения
     * данных о животных. Создание меню и получение списка доступных видов животных.
     * @param view класс, реализующий интерфейс View
     * @param repository класс, реализующий интерфейс AnimalRepository
     */
    public AnimalController(View view, AnimalRepository repository) {
        this.view = view;
        this.service = new AnimalService(repository);
        this.menu = new ArrayList<>();
        this.animalTypes = new ArrayList<>();
        createMenuList();
        createAnimalTypesList();
    }

    /**
     * Запуск приложения для работы с питомником
     */
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
                    case 4 -> {
                        view.println("Укажите ID животного");
                        Animal animal = getAnimalById(getLongValue(1, Long.MAX_VALUE));
                        if (animal == null) {
                            view.println("Животное с указанным ID не найдено");
                        } else {
                            showListCommandsOfAnimal(animal);
                        }
                    }
                    case 5 -> {
                        view.println("Укажите ID животного");
                        Animal animal = getAnimalById(getLongValue(1, Long.MAX_VALUE));
                        if (animal == null) {
                            view.println("Животное с указанным ID не найдено");
                        } else {
                            showListCommandsOfAnimal(animal);
                            addCommandsToAnimal(animal);
                        }
                    }
                }
            } while (item != 0);
        } catch (IOException e) {
            view.println(e.getMessage());
        }
        view.println("\nДо новых встреч!");
    }

    /**
     * Заполнение списка доступных пунктов меню приложения.
     */
    private void createMenuList() {
        menu.add("Завершить работу и выйти");
        menu.add("Посмотреть список всех животных питомника");
        menu.add("Посмотреть список животных питомника определенного вида");
        menu.add("Добавить в питомник новое животное");
        menu.add("Посмотреть список команд, которые выполняет животное");
        menu.add("Обучить животное новым командам");
    }

    /**
     * Получение списка доступных видов животных.
     */
    private void createAnimalTypesList() {
        animalTypes.addAll(service.getAnimalTypes());
    }

    /**
     * Выбрать пункт из списка (типа int), нумерация которого начинается и заканчивается
     * указанными значениями.
     * @param minValue минимальный пункт списка
     * @param maxValue максимальный пункт списка
     * @return выбранный пункт
     */
    private int selectMenuItem(int minValue, int maxValue) {
        view.print("Введите число: ");
        try {
            return view.getInt(minValue, maxValue);
        } catch (IncorrectDataException e) {
            view.println("Ошибка ввода: " + e.getMessage());
            return selectMenuItem(minValue, maxValue);
        }
    }

    /**
     * Получение числа типа long в указанном диапазоне.
     * @param minValue минимальное значение
     * @param maxValue максимальное значение
     * @return число из диапазона
     */
    private long getLongValue(long minValue, long maxValue) {
        view.print("Введите число: ");
        try {
            return view.getLong(minValue, maxValue);
        } catch (IncorrectDataException e) {
            view.println("Ошибка ввода: " + e.getMessage());
            return getLongValue(minValue, maxValue);
        }
    }

    /**
     * Вывод информации о всех сохраненных животных
     */
    private void showAllAnimals() {
        view.println("<<< Список животных питомника >>>");
        view.printList(service.getAllAnimals());
    }

    /**
     * Выбор вида животного и вывод информации о животных данного вида.
     */
    private void showAnimalsByType() {
        int typeIndex = selectAnimalTypeIndex();
        view.println("<<< Список животных вида " + animalTypes.get(typeIndex) + " >>>");
        List<Animal> animalsByType = service.getAnimalsByType(animalTypes.get(typeIndex));
        if (animalsByType.isEmpty()) {
            view.println("Никого не нашли...");
        } else {
            view.printList(animalsByType);
        }
    }

    /**
     * Добавление нового животного в питомник. Выбор вида животного, задание его имени,
     * даты рождения, списка выполняемых команд. При успешном добавлении животного
     * увеличивает счетчик.
     * @param counter объект класса счетчика
     */
    private void addNewAnimal(Counter counter) {
        try {
            int typeIndex = selectAnimalTypeIndex();
            String name = getAnimalName();
            LocalDate birthday = getAnimalBirthday(50);
            Animal animal = service.addNewAnimal(animalTypes.get(typeIndex), name, birthday);

            boolean goNext = confirm("Хотите добавить выполняемые команды (0 - нет, 1 - да)?");
            if (goNext) {
                addCommandsToAnimal(animal);
            }
            view.println("Добавлено животное: ");
            view.println(animal.toString());
            counter.add();
        } catch (Exception e) {
            view.println(e.getMessage());
        }
    }

    /**
     * Выбор вида животного из списка доступных видов.
     * @return индекс выбранного вида в списке доступных видов животных
     */
    private int selectAnimalTypeIndex() {
        view.println("Выберите вид животного");
        view.printNumberedList(animalTypes);
        return selectMenuItem(1, animalTypes.size()) - 1;
    }

    /**
     * Получение животного с указанным ID.
     * @param id уникальный идентификатор животного
     * @return животное с указанным ID или null, если не было найдено
     */
    private Animal getAnimalById(long id) {
        return service.getAnimalById(id);
    }

    /**
     * Вывод краткой информации о животном и его списка команд.
     * @param animal объект животного
     */
    private void showListCommandsOfAnimal(Animal animal) {
        view.println("Животное: " + animal.getShortInfo());
        view.println("Список команд: " + animal.getCommands());
    }

    /**
     * Добавление выполняемых животным команд.
     * @param animal объект животного
     */
    private void addCommandsToAnimal(Animal animal) {
        List<Command> animalCommands = animal.getCommands();
        boolean goNext = true;
        while (goNext) {
            addCommandToList(service.getAllCommands(), animalCommands);
            view.println("Текущий список команд: " + animalCommands);
            goNext = confirm("Хотите добавить еще команду (0 - нет, 1 - да)?");
        }
        service.addOrUpdateAnimal(animal);
        view.println("Животное: " + animal);
    }

    /**
     * Вывод сообщения и получение подтверждения действия.
     * @param message сообщение для подтверждения
     * @return результат выбора
     */
    private boolean confirm(String message) {
        view.println(message);
        return selectMenuItem(0, 1) != 0;
    }

    /**
     * Добавить команду из общего списка команд в список команд животного. Возможно
     * создание и добавление новой команды.
     * @param allCommands список всех доступных команд
     * @param animalCommands список команд животного
     */
    private void addCommandToList(
            List<Command> allCommands, List<Command> animalCommands) {
        view.println("Выберите команду, которую нужно добавить животному");
        view.println("Список доступных команд:");
        view.println("0. Добавить новую команду, которой нет в списке");
        view.printNumberedList(allCommands);
        int commandId = selectMenuItem(0, allCommands.size());
        if (commandId == 0) {
            Command command = createNewCommand();
            if (command != null) {
                animalCommands.add(command);
            }
        } else {
            Command command = allCommands.get(commandId - 1);
            if (animalCommands.contains(command)) {
                view.println("Такая команда уже добавлена в список");
            } else {
                animalCommands.add(command);
            }
        }
    }

    /**
     * Создание новой команды, которой нет в списке доступных команд.
     * @return созданная команда или null, если нельзя создать команду с таким названием
     */
    private Command createNewCommand() {
        try {
            view.print("Введите название команды: ");
            String commandName = view.getMessage();
            Command command = service.createNewCommand(commandName);
            view.println("Добавлена команда '" + command.getName() + "'");
            return command;
        } catch (RuntimeException e) {
            view.println("Ошибка ввода: " + e.getMessage());
            return null;
        }
    }

    /**
     * Получение клички животного.
     * @return валидная кличка животного
     */
    private String getAnimalName() {
        view.print("Введите кличку животного: ");
        String name = view.getMessage();
        try {
            service.isCorrectName(name);
            return name;
        } catch (RuntimeException e) {
            view.println("Ошибка ввода: " + e.getMessage());
            return getAnimalName();
        }
    }

    /**
     * Получение даты рождения животного в формате гггг-мм-дд.
     * @param maxAge ограничение максимального возраста животного (в годах)
     * @return дата рождения животного
     */
    private LocalDate getAnimalBirthday(int maxAge) {
        view.print("Введите дату рождения животного в формате гггг-мм-дд: ");
        String birthday = view.getMessage().trim();
        try {
            LocalDate date = LocalDate.parse(birthday);
            if (date.isAfter(LocalDate.now())
                    || date.isBefore(LocalDate.now().minusYears(maxAge))) {
                view.println("Ошибка ввода: укажите корректную дату");
                getAnimalBirthday(maxAge);
            }
            return date;
        } catch (DateTimeParseException e) {
            view.println("Ошибка ввода: неверный формат даты");
            return getAnimalBirthday(maxAge);
        }
    }
}
