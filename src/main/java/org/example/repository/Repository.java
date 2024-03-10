package org.example.repository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс репозитория
 * @param <T> тип сущности
 * @param <TId> тип ID сущности
 */
public interface Repository<T, TId> {

    /**
     * Получение списка всех записей.
     * @return список записей
     */
    List<T> findAll();

    /**
     * Сохранение или обновление сущности в репозитории.
     * @param entity сущность для сохранения или обновления
     * @return сохраненная сущность
     */
    T save(T entity);

    /**
     * Поиск сущности с указанным ID.
     * @param id уникальный идентификатор сущности
     * @return Optional объект сущности с указанным ID
     */
    Optional<T> findById(TId id);

}
