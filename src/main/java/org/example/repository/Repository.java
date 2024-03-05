package org.example.repository;

import java.util.List;

/**
 * Интерфейс репозитория
 */
public interface Repository<T, TId> {

    List<T> findAll();

    <E extends T> E save(E entity);

}
