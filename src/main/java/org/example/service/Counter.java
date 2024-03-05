package org.example.service;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicLong;

/**
 *
 */
@Getter
public class Counter implements AutoCloseable {

    private String pathToLogFile = "data/";

    private String fileName = "counter.txt";

    private final AtomicLong counter;

    public Counter() {
        this.counter = new AtomicLong();
    }

    public Long add() {
        return counter.incrementAndGet();
    }

    @Override
    public void close() throws Exception {
        // TODO
    }
}
