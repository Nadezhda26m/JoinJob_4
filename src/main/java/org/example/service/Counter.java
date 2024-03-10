package org.example.service;

import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Класс счетчика
 */
@Getter
public class Counter implements AutoCloseable {

    /**
     * Относительный путь до папки, где будут храниться файлы с логами
     */
    private final String pathToLogDir = "data" + File.separatorChar;

    /**
     * Название файла для записи информации о счетчике
     */
    private final String fileName = "counter.txt";

    /**
     * Объект класса для записи потоков символов в файл
     */
    private FileWriter fileWriter;

    /**
     * Счетчик
     */
    private AtomicInteger counter;

    /**
     * Конструктор класса для инициализации счетчика и создания подключения к файлу.
     * @throws IOException ошибка подключения к файлу
     */
    public Counter() throws IOException {
        this.counter = new AtomicInteger();
        connectToFile();
    }

    /**
     * Увеличение счетчика на 1 и запись значения в файл.
     * @throws IOException ошибка записи в файл
     */
    public void add() throws IOException {
        fileWriter.write("counter = " + counter.incrementAndGet() + '\n');
        fileWriter.flush();
    }

    /**
     * Закрытие всех открытых подключений к ресурсам счетчика.
     * Вывод информации о закрытии в консоль.
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        fileWriter.close();
        Logger.getAnonymousLogger().info("Counter closed");
    }

    /**
     * Создание директорий для хранения логов, если еще не были созданы.
     * Открытие потока для записи в файл.
     * @throws IOException ошибка подключения к файлу
     */
    private void connectToFile() throws IOException {
        File file = new File(pathToLogDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        fileWriter = new FileWriter(
                pathToLogDir + fileName, StandardCharsets.UTF_8, false);
    }

}
