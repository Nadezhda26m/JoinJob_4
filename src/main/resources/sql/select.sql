# Выборка всех групп
-- 1	Домашние животные
SELECT id, agroup AS 'Группа' FROM animals;


# Выборка всех групп и видов
-- 1	Домашние животные	Собаки
SELECT 
	t.id AS 'Id', 
    a.agroup AS 'Группа',
	t.atype AS 'Вид' 
FROM animals a, types_animals t
WHERE a.id = t.group_id;


# Объединение таблиц со всеми животными
-- 1	1	Шарик	2015-03-29
-- 1	2	Мурзик	2015-03-29
SELECT id, type_id, name, birthday
FROM dogs
UNION
SELECT id, type_id, name, birthday
FROM cats
UNION
SELECT id, type_id, name, birthday
FROM hamsters
UNION
SELECT id, type_id, name, birthday
FROM horses
UNION
SELECT id, type_id, name, birthday
FROM camels
UNION
SELECT id, type_id, name, birthday
FROM donkeys
ORDER BY type_id, id;


# Объединение информации о группе и виде животного с информацией о нем
-- 1	Домашние животные	Собаки	Шарик	2015-03-29
SELECT 
	d.id, 
    a.agroup AS 'Группа', 
    t.atype AS 'Вид', 
    d.name AS 'Имя',
    d.birthday AS 'Дата_рожд'
FROM dogs d, animals a, types_animals t
WHERE d.type_id = t.id AND t.group_id = a.id
ORDER BY d.id;


# Вывод имени животного и его изученных команд
-- Бобик	Лежать, Нельзя, Место
SELECT d.name, GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды'
FROM dogs d
JOIN animals_commands ac
ON ac.animal_id = d.id AND ac.animal_type = d.type_id
JOIN commands c
ON c.id = ac.command_id
GROUP BY d.name;


# Вывод всей информации о собаках
-- Домашние животные	Собаки	Бобик	2015-03-29	Лежать, Нельзя, Место
SELECT a.agroup, ta.atype, d.name, d.birthday, GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды'
FROM dogs d
RIGHT JOIN types_animals ta
ON ta.id = d.type_id
RIGHT JOIN animals a
ON a.id = ta.group_id
JOIN animals_commands ac
ON ac.animal_id = d.id AND ac.animal_type = d.type_id
JOIN commands c
ON c.id = ac.command_id
GROUP BY a.agroup, ta.atype, d.name, d.birthday;


# Сводная таблица
-- Домашние животные	Кошки	Мурзик	2015-12-04	Лежать
SELECT 
	animal.agroup AS 'Группа', 
    animal.atype AS 'Тип', 
    animal.name AS 'Кличка', 
    animal.birthday AS 'Д/р', 
    GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды' 
FROM (
		SELECT dog.id, dog.type_id, a.agroup, ta.atype, dog.name, dog.birthday
		FROM animals a, types_animals ta, dogs dog
		WHERE a.id = ta.group_id AND ta.id = dog.type_id
		UNION
		SELECT cat.id, cat.type_id, a.agroup, ta.atype, cat.name, cat.birthday
		FROM animals a, types_animals ta, cats cat
		WHERE a.id = ta.group_id AND ta.id = cat.type_id
        UNION
		SELECT hamster.id, hamster.type_id, a.agroup, ta.atype, hamster.name, hamster.birthday
		FROM animals a, types_animals ta, hamsters hamster
		WHERE a.id = ta.group_id AND ta.id = hamster.type_id
        UNION
		SELECT horse.id, horse.type_id, a.agroup, ta.atype, horse.name, horse.birthday
		FROM animals a, types_animals ta, horses horse
		WHERE a.id = ta.group_id AND ta.id = horse.type_id
        UNION
		SELECT camel.id, camel.type_id, a.agroup, ta.atype, camel.name, camel.birthday
		FROM animals a, types_animals ta, camels camel
		WHERE a.id = ta.group_id AND ta.id = camel.type_id
        UNION
		SELECT donkey.id, donkey.type_id, a.agroup, ta.atype, donkey.name, donkey.birthday
		FROM animals a, types_animals ta, donkeys donkey
		WHERE a.id = ta.group_id AND ta.id = donkey.type_id
	) AS animal
LEFT JOIN animals_commands ac
ON ac.animal_id = animal.id AND ac.animal_type = animal.type_id
LEFT JOIN commands c
ON c.id = ac.command_id
GROUP BY animal.agroup, animal.atype, animal.name, animal.birthday
ORDER BY animal.agroup DESC, animal.atype, animal.name;

