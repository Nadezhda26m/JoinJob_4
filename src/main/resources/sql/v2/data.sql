# Заполняем таблицы из schema.sql начальными данными
INSERT INTO groups_animals(agroup) VALUES
    ('Домашние животные'),
    ('Вьючные животные');
    
INSERT INTO types_animals(group_id, atype) VALUES
    (1, 'Собаки'),
    (1, 'Кошки'),
    (1, 'Хомяки'),
    (2, 'Лошади'),
    (2, 'Верблюды'),
    (2, 'Ослы');
    
INSERT INTO animals(type_id, name, birthday) VALUES
    (1, 'Шарик', DATE_FORMAT(SUBDATE(CURRENT_DATE(), INTERVAL '1 2' YEAR_MONTH), '%Y-%m-%d')),
    (1, 'Бобик', DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL '6 1' YEAR_MONTH), '%Y-%m-%d')),
    (1, 'Пират', '2019-03-29'),
    (2, 'Мурзик', DATE_FORMAT(SUBDATE(CURRENT_DATE, INTERVAL '8 3' YEAR_MONTH), '%Y-%m-%d')),
    (2, 'Тузик', DATE_FORMAT(SUBDATE(NOW(), INTERVAL '2 5' YEAR_MONTH), '%Y-%m-%d')),
    (2, 'Мальвина', DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL '1 9' YEAR_MONTH), '%Y-%m-%d')),
    (3, 'Грызлик', DATE_FORMAT(SUBDATE(NOW(), INTERVAL 5 MONTH), '%Y-%m-%d')),
    (3, 'Хома', DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 7 QUARTER), '%Y-%m-%d')), -- 3 MONTH * 7
    (3, 'Пухлик', DATE_FORMAT(SUBDATE(CURRENT_DATE(), 94), '%Y-%m-%d')), -- default days
    (4, 'Ветер', DATE_FORMAT(DATE_SUB(CURRENT_DATE, INTERVAL 18 YEAR), '%Y-%m-%d')),
    (4, 'Стрела', DATE_FORMAT(SUBDATE(SYSDATE(), INTERVAL '2 11' YEAR_MONTH), '%Y-%m-%d')),
    (4, 'Джеки', DATE_FORMAT(SUBDATE(LOCALTIMESTAMP(), INTERVAL '11 5' YEAR_MONTH), '%Y-%m-%d')),
    (5, 'Верблюд1', '2015-03-29'),
    (5, 'Верблюд2', '2016-03-29'),
    (5, 'Верблюд3', '2017-03-29'),
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
    
INSERT INTO animals_commands(animal_id, command_id) 
VALUES
    (1, 1), (1, 3), (1, 5), (1, 7), (1, 8), (2, 3), (2, 7), (2, 8), (3, 4), (3, 6),
    (4, 3), (5, 3), (5, 7),
    (10, 2), (10, 9), (10, 11), (11, 9), (11, 11), (12, 2), (12, 9), (12, 10), (12, 11),
    (13, 9), (13, 11), (14, 9), (14, 11), (15, 9), (15, 11),
    (16, 10), (17, 10), (18, 10);
