package org.example.service;

import lombok.Getter;
import org.example.exception.IncorrectDataException;
import org.example.model.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Сервис для работы с командами
 */
@Getter
public class CommandsService {

    /**
     * Список всех доступных команд
     */
    private final List<Command> commandsList;

    /**
     * Конструктор для инициализации списка всех доступных команд
     */
    public CommandsService() {
        this.commandsList = new ArrayList<>();
        init();
    }

    /**
     * Создание новой команды и добавление ее в список доступных команд, если такой команды еще нет.
     * Может вызвать исключение IncorrectDataException, если команда уже существует.
     * @param name название команды
     * @return созданная команда
     */
    public Command createNewCommand(String name) {
        checkCorrectName(name);
        Command command = new Command(name.trim().toLowerCase());
        if (commandsList.contains(command))
            throw new IncorrectDataException("Данная команда уже существует");
        commandsList.add(command);
        return command;
    }

    /**
     * Получение списка всех доступных команд, отсортированного по алфавиту.
     * @return отсортированный список команд
     */
    public List<Command> getSortedList() {
        return commandsList.stream().sorted().toList();
    }

    /**
     * Проверка корректности названия команды. Название не должно быть пустым или
     * иметь длину более 99 символов, должно быть записано русскими буквами, допускается
     * перечисление через пробел нескольких слов, первое слово должно быть длиной
     * не менее 2 символов.
     * @param name название команды для проверки на корректность
     */
    private void checkCorrectName(String name) {
        if (name == null || name.isEmpty())
            throw new NullPointerException("Название команды не задано");
        if (!name.trim().toLowerCase().matches("[а-яё]{2,}(\s[а-яё]+)*"))
            throw new IncorrectDataException(
                    "Некорректные данные. Нельзя создать команду '" + name + '\'');
        if (name.trim().length() > 100)
            throw new IncorrectDataException("Слишком длинное название команды");
    }

    /**
     * Создание начального списка команд.
     */
    private void init() {
        commandsList.addAll(Arrays.asList(
                new Command("сидеть"),
                new Command("право"),
                new Command("лежать"),
                new Command("голос"),
                new Command("рядом"),
                new Command("ко мне"),
                new Command("нельзя"),
                new Command("место"),
                new Command("стоп"),
                new Command("хоп"),
                new Command("вперед")
        ));
    }

}
