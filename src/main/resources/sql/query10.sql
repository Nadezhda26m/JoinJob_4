/* 10. Удалить из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади и ослы в одну таблицу */

# Удаление вида 'Верблюды' и каскадное удаление информации из таблицы camels
DELETE FROM types_animals WHERE atype = 'Верблюды';

SELECT * FROM types_animals;

SELECT * FROM camels;

-- DROP TABLE camels; -- нарушатся прошлые запросы

# Удаление информации о командах, выполняемых верблюдами
DELETE FROM animals_commands WHERE animal_type = 5;

SELECT * FROM animals_commands ORDER BY animal_type;

# Объединенная выборка информации по лошадям и ослам
-- Лошади и ослы
SELECT a.agroup, ta.atype, horse.name, horse.birthday
FROM animals a, types_animals ta, horses horse
WHERE a.id = ta.group_id AND ta.id = horse.type_id
UNION
SELECT a.agroup, ta.atype, donkey.name, donkey.birthday
FROM animals a, types_animals ta, donkeys donkey
WHERE a.id = ta.group_id AND ta.id = donkey.type_id;

# Новая таблица с данными из таблиц horses и donkeys, новым ID и ссылкой на таблицу с видами
CREATE TABLE pack_animals AS
SELECT * FROM horses
UNION
SELECT * FROM donkeys;

ALTER TABLE pack_animals ADD new_id INT PRIMARY KEY AUTO_INCREMENT FIRST;
ALTER TABLE pack_animals ADD FOREIGN KEY(type_id) REFERENCES types_animals(id);

SELECT * FROM pack_animals;

-- DROP TABLE horses;
-- DROP TABLE donkeys;


-- -----------------------------------------------------------

/* 11. Создать новую таблицу "молодые животные" в которую попадут все
животные старше 1 года, но младше 3 лет, и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице */

CREATE TABLE young_animals AS
SELECT 
    animal.id,
    animal.type_id,
    animal.name,
    animal.birthday,
    CAST(
        CONCAT(
            TIMESTAMPDIFF(MONTH, animal.birthday, NOW()) DIV 12, ' г. ', -- целочисленное деление
            TIMESTAMPDIFF(MONTH, animal.birthday, NOW()) MOD 12, ' мес.' -- остаток
		) AS CHAR(20) 
    ) AS age
FROM (
    SELECT *
    FROM dogs
    WHERE TIMESTAMPDIFF(MONTH, birthday, now()) > 12 AND TIMESTAMPDIFF(MONTH, birthday, now()) < 80
    UNION
    SELECT *
    FROM cats
    UNION
    SELECT *
    FROM hamsters
    WHERE 
        period_diff(EXTRACT(YEAR_MONTH FROM NOW()), date_format(birthday, '%Y%m')) < 36 -- in MONTH
        AND
        (12 * (YEAR(NOW()) - YEAR(birthday)) + (MONTH(NOW()) - MONTH(birthday))) > 4
    UNION
    SELECT id, type_id, name, birthday
    FROM pack_animals
) AS animal
-- WHERE TIMESTAMPDIFF(MONTH, animal.birthday, now()) BETWEEN 12 AND 36 AND TIMESTAMPDIFF(MONTH, animal.birthday, now()) NOT IN (36);
WHERE animal.birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);

ALTER TABLE young_animals ADD PRIMARY KEY(id, type_id);
DESCRIBE young_animals;
-- SHOW COLUMNS FROM young_animals;
-- SHOW CREATE TABLE young_animals;

# Выборка из полученной таблицы young_animals с информацией из связанных таблиц
-- Домашние животные	Собаки	Шарик	2023-01-04	1 г. 2 мес.	Сидеть, Лежать, Рядом, Нельзя, Место
SELECT 
    a.agroup AS 'Группа', 
    ta.atype AS 'Тип', 
    animal.name AS 'Кличка', 
    animal.birthday AS 'Д/р', 
    animal.age AS 'Возраст',
    GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды' 
FROM animals a, types_animals ta, young_animals animal
LEFT JOIN animals_commands ac
ON ac.animal_id = animal.id AND ac.animal_type = animal.type_id
LEFT JOIN commands c
ON c.id = ac.command_id
WHERE a.id = ta.group_id AND ta.id = animal.type_id
GROUP BY a.agroup, ta.atype, animal.name, animal.birthday, animal.age
ORDER BY a.agroup DESC, animal.birthday DESC;



-- -----------------------------------------------------------

/* 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам. */

-- 1 вариант
CREATE TABLE all_animals AS
SELECT * FROM (
    SELECT *, 'dogs' AS from_table FROM dogs
    UNION
    SELECT *, 'cats' AS from_table FROM cats
    UNION
    SELECT *, 'hamsters' AS from_table FROM hamsters
    UNION
    SELECT id, type_id, name, birthday, 'pack_animals' AS from_table FROM pack_animals
) AS animal;

SELECT
    a.agroup AS 'Группа', 
    ta.atype AS 'Тип',
    animal.from_table AS 'Таблица', 
    animal.id AS 'Last ID',
    animal.name AS 'Кличка', 
    animal.birthday AS 'Д/р', 
    GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды'
FROM animals a, types_animals ta, all_animals animal -- таблица 1
LEFT JOIN animals_commands ac
ON ac.animal_id = animal.id AND ac.animal_type = animal.type_id
LEFT JOIN commands c
ON c.id = ac.command_id
WHERE a.id = ta.group_id AND ta.id = animal.type_id
GROUP BY a.agroup, ta.atype, animal.from_table, animal.id, animal.name, animal.birthday
ORDER BY a.agroup DESC, ta.atype, animal.name;


-- 2 вариант
CREATE TABLE all_animals2 AS SELECT * FROM dogs WHERE 1 = 0; -- копирование структуры таблицы без данных

ALTER TABLE all_animals2 ADD new_id INT PRIMARY KEY AUTO_INCREMENT FIRST;
ALTER TABLE all_animals2 ADD from_table VARCHAR(27);
ALTER TABLE all_animals2 ADD CONSTRAINT all_animals2_fk_type_id FOREIGN KEY(type_id) REFERENCES types_animals(id);

INSERT INTO all_animals2(id, type_id, name, birthday, from_table) SELECT *, 'dogs' AS from_table FROM dogs;
INSERT INTO all_animals2(id, type_id, name, birthday, from_table) SELECT *, 'cats' FROM cats;
INSERT INTO all_animals2 SELECT null, id, type_id, name, birthday, 'hamsters' FROM hamsters;
INSERT INTO all_animals2 SELECT null, id, type_id, name, birthday, 'pack_animals' FROM pack_animals;

SELECT 
    animal.new_id AS 'ID',
    a.agroup AS 'Группа', 
    ta.atype AS 'Тип',
    animal.from_table AS 'Таблица', 
    animal.id AS 'Last ID',
    animal.name AS 'Кличка', 
    animal.birthday AS 'Д/р', 
    GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды'
FROM animals a, types_animals ta, all_animals2 animal -- таблица 2
LEFT JOIN animals_commands ac
ON ac.animal_id = animal.id AND ac.animal_type = animal.type_id
LEFT JOIN commands c
ON c.id = ac.command_id
WHERE a.id = ta.group_id AND ta.id = animal.type_id
GROUP BY animal.new_id, a.agroup, ta.atype, animal.from_table, animal.id, animal.name, animal.birthday
ORDER BY animal.new_id;

DESCRIBE all_animals2;


