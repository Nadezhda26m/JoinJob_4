package org.example.service;

import lombok.Getter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 *
 */
@Getter
public class Counter implements AutoCloseable {

    private final String pathToLogFile = "data" + File.separatorChar;

    private final String fileName = "counter.txt";

    private FileWriter fileWriter;

    private int counter;

    public Counter() throws IOException {
        this.counter = 0;
        connectToFile();
    }

    public void add() throws IOException {
        fileWriter.write("counter = " + ++counter + '\n');
        fileWriter.flush();
    }

    @Override
    public void close() throws IOException {
        fileWriter.close();
        Logger.getAnonymousLogger().info("Counter closed");
    }

    private void connectToFile() throws IOException {
        File file = new File(pathToLogFile);
        if (!file.exists()) {
            file.mkdirs();
        }
        fileWriter = new FileWriter(
                pathToLogFile + fileName, StandardCharsets.UTF_8, false);
    }

}
