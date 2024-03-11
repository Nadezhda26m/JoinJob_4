# Заполняем таблицы из schema.sql начальными данными
INSERT INTO animals(agroup) VALUES
    ('Домашние животные'),
    ('Вьючные животные');
    
INSERT INTO types_animals(group_id, atype) VALUES
    (1, 'Собаки'),
    (1, 'Кошки'),
    (1, 'Хомяки'),
    (2, 'Лошади'),
    (2, 'Верблюды'),
    (2, 'Ослы');
    
INSERT INTO dogs(type_id, name, birthday) VALUES
    (1, 'Шарик', DATE_FORMAT(SUBDATE(CURRENT_DATE(), INTERVAL '1 2' YEAR_MONTH), '%Y-%m-%d')),
    (1, 'Бобик', DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL '6 1' YEAR_MONTH), '%Y-%m-%d')),
    (1, 'Пират', '2019-03-29');
    
INSERT INTO cats(type_id, name, birthday) VALUES
    (2, 'Мурзик', DATE_FORMAT(SUBDATE(CURRENT_DATE, INTERVAL '8 3' YEAR_MONTH), '%Y-%m-%d')),
    (2, 'Тузик', DATE_FORMAT(SUBDATE(NOW(), INTERVAL '2 5' YEAR_MONTH), '%Y-%m-%d')),
    (2, 'Мальвина', DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL '1 9' YEAR_MONTH), '%Y-%m-%d'));
    
INSERT INTO hamsters(type_id, name, birthday) VALUES
    (3, 'Грызлик', DATE_FORMAT(SUBDATE(NOW(), INTERVAL 5 MONTH), '%Y-%m-%d')),
    (3, 'Хома', DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 7 QUARTER), '%Y-%m-%d')), -- 3 MONTH * 7
    (3, 'Пухлик', DATE_FORMAT(SUBDATE(CURRENT_DATE(), 94), '%Y-%m-%d')); -- default days
    
INSERT INTO horses(type_id, name, birthday) VALUES
    (4, 'Ветер', DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 18 YEAR), '%Y-%m-%d')),
    (4, 'Стрела', DATE_FORMAT(SUBDATE(SYSDATE(), INTERVAL '2 11' YEAR_MONTH), '%Y-%m-%d')),
    (4, 'Джеки', DATE_FORMAT(SUBDATE(LOCALTIMESTAMP(), INTERVAL '11 5' YEAR_MONTH), '%Y-%m-%d'));
    
INSERT INTO camels(type_id, name, birthday) VALUES
    (5, 'Верблюд1', '2015-03-29'),
    (5, 'Верблюд2', '2016-03-29'),
    (5, 'Верблюд3', '2017-03-29');
    
INSERT INTO donkeys(type_id, name, birthday) VALUES
    (6, 'Иаа', DATE_FORMAT(SUBDATE(LOCALTIME, INTERVAL 135 WEEK), '%Y-%m-%d')),
    (6, 'Осел2', DATE_FORMAT(SUBDATE(LOCALTIME(), INTERVAL 24 YEAR), '%Y-%m-%d')),
    (6, 'Ослик3', DATE_FORMAT(SUBDATE(LOCALTIMESTAMP, INTERVAL '10 2' YEAR_MONTH), '%Y-%m-%d'));
    
INSERT INTO commands(name) VALUES
    ('Сидеть'),
    ('Право'),
    ('Лежать'),
    ('Голос'),
    ('Рядом'),
    ('Ко мне'),
    ('Нельзя'),
    ('Место'),
    ('Стоп'),
    ('Хоп'),
    ('Вперед');
    
INSERT INTO animals_commands(animal_id, animal_type, command_id) 
VALUES
    (1, 1, 1), (1, 1, 3), (1, 1, 5), (1, 1, 7), (1, 1, 8), (2, 1, 3), (2, 1, 7), (2, 1, 8), (3, 1, 4), (3, 1, 6),
    (1, 2, 3), (2, 2, 3), (2, 2, 7),
    (1, 4, 2), (1, 4, 9), (1, 4, 11), (2, 4, 9), (2, 4, 11), (3, 4, 2), (3, 4, 9), (3, 4, 10), (3, 4, 11), 
    (1, 5, 9), (1, 5, 11), (2, 5, 9), (2, 5, 11), (3, 5, 9), (3, 5, 11),
    (1, 6, 10), (2, 6, 10), (3, 6, 10);
