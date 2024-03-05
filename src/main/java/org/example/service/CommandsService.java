package org.example.service;

import lombok.Getter;
import org.example.exception.IncorrectDataException;
import org.example.model.Command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class CommandsService {

    private final List<Command> commandsList;

    public CommandsService() {
        this.commandsList = new ArrayList<>();
        init();
    }

    public boolean addNewCommand(String name) {
        checkCorrectName(name);
        Command command = new Command(name.trim().toLowerCase());
        if (commandsList.contains(command))
            return false;
        return commandsList.add(command);
    }

    public List<Command> getSortedList() {
        return commandsList.stream().sorted().toList();
    }

    private void checkCorrectName(String name) {
        if (name == null || name.isEmpty())
            throw new NullPointerException("Название команды не задано");
        if (!name.trim().toLowerCase().matches("[а-яё]+[\s[а-яё]+]*")) // "[а-яё]+[\s{1}[а-яё]+]*"
            throw new IncorrectDataException(
                    "Некорректные данные. Нельзя создать команду '" + name + '\'');
    }

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

    @Override
    public String toString() {
        return commandsList.toString();
    }

}
