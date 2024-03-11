# Выборка всех групп
-- 1	Домашние животные
SELECT id, agroup AS 'Группа' FROM groups_animals;


# Выборка всех групп и видов
-- 1	Домашние животные	Собаки
SELECT 
	ta.id AS 'Id', 
    ga.agroup AS 'Группа',
	ta.atype AS 'Вид' 
FROM groups_animals ga, types_animals ta
WHERE ga.id = ta.group_id;


# Сводная таблица
-- Домашние животные	Кошки	Мальвина    2022-06-11	
-- Домашние животные	Кошки	Мурзик      2015-12-11	Лежать 
SELECT 
	ga.agroup AS 'Группа', 
    ta.atype AS 'Вид', 
    a.name AS 'Кличка', 
    a.birthday AS 'Д/р', 
    GROUP_CONCAT(c.name SEPARATOR ', ') AS 'Команды' 
FROM groups_animals ga, types_animals ta, animals a -- порядок важен
LEFT JOIN animals_commands ac
ON ac.animal_id = a.id
LEFT JOIN commands c
ON c.id = ac.command_id
WHERE ta.id = a.type_id AND ga.id = ta.group_id
GROUP BY ga.agroup, ta.atype, a.name, a.birthday
ORDER BY ga.agroup DESC, ta.atype, a.name;
