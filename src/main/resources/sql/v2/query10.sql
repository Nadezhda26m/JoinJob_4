/* 10. Удалить из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади и ослы в одну таблицу */

# Удаление вида 'Верблюды' и каскадное удаление информации из таблиц animals и animals_commands
DELETE FROM types_animals WHERE atype = 'Верблюды';

SELECT * FROM types_animals;

SELECT * FROM animals_commands ORDER BY animal_id;


# Объединенная выборка информации по лошадям и ослам
-- Лошади	Ветер	2006-03-11	Право, Стоп, Вперед
SELECT 
    ta.atype AS 'Вид', 
    a.name AS 'Кличка', 
    a.birthday AS 'Д/р', 
    GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды' 
FROM types_animals ta, animals a
LEFT JOIN animals_commands ac
ON ac.animal_id = a.id
LEFT JOIN commands c
ON c.id = ac.command_id
WHERE ta.id = a.type_id AND (a.type_id = 4 OR a.type_id = 6)
GROUP BY ta.atype, a.name, a.birthday
ORDER BY a.name;


# Новая таблица с данными по лошадям и ослам и структурой таблицы animals

CREATE TABLE pack_animals LIKE animals;

INSERT INTO pack_animals 
SELECT * 
FROM animals 
WHERE type_id = 4 OR type_id = 6;

-- 10	Вьючные животные	Лошади	Ветер	2006-03-11
SELECT 
    pa.id AS 'ID',
    ga.agroup AS 'Группа', 
    ta.atype AS 'Вид', 
    pa.name AS 'Кличка', 
    pa.birthday AS 'Д/р'
FROM pack_animals pa, groups_animals ga, types_animals ta
WHERE ga.id = ta.group_id AND ta.id = pa.type_id
ORDER BY pa.id;


-- -----------------------------------------------------------

/* 11. Создать новую таблицу "молодые животные" в которую попадут все
животные старше 1 года, но младше 3 лет, и в отдельном столбце с точностью
до месяца подсчитать возраст животных в новой таблице */

CREATE TABLE young_animals AS
SELECT 
    id AS animals_id,
    type_id,
    name,
    birthday,
    CAST(
        CONCAT(
            TIMESTAMPDIFF(MONTH, birthday, NOW()) DIV 12, ' г. ', -- целочисленное деление
            TIMESTAMPDIFF(MONTH, birthday, NOW()) MOD 12, ' мес.' -- остаток
		) AS CHAR(20)
    ) AS age
FROM animals
WHERE birthday BETWEEN ADDDATE(curdate(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);

ALTER TABLE young_animals ADD id INT PRIMARY KEY AUTO_INCREMENT FIRST;
ALTER TABLE young_animals ADD FOREIGN KEY(type_id) REFERENCES types_animals(id);
ALTER TABLE young_animals ADD FOREIGN KEY(animals_id) REFERENCES animals(id);

DESCRIBE young_animals;


# Выборка из полученной таблицы young_animals с информацией из связанных таблиц
-- 1	Домашние животные	Собаки	Шарик	2023-01-11	1 г. 2 мес.	Сидеть, Лежать, Рядом, Нельзя, Место
-- 3	Домашние животные	Кошки	Мальвина	2022-06-11	1 г. 9 мес.	
SELECT 
    ya.id AS 'ID',
    ga.agroup AS 'Группа', 
    ta.atype AS 'Тип', 
    ya.name AS 'Кличка', 
    ya.birthday AS 'Д/р', 
    ya.age AS 'Возраст',
    GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды' 
FROM groups_animals ga, types_animals ta, young_animals ya
LEFT JOIN animals_commands ac
ON ac.animal_id = ya.animals_id
LEFT JOIN commands c
ON c.id = ac.command_id
WHERE ga.id = ta.group_id AND ta.id = ya.type_id
GROUP BY ya.id, ga.agroup, ta.atype, ya.name, ya.birthday, ya.age
ORDER BY ya.birthday DESC;


-- -----------------------------------------------------------

/* 12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на
прошлую принадлежность к старым таблицам. */

-- они уже в одной таблице, общие выборки написаны выше

