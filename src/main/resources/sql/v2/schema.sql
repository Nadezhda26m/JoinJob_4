# Создаем базу данных friendsman
DROP DATABASE IF EXISTS friendsman;
CREATE DATABASE friendsman;
USE friendsman;

# Создаем таблицу с группами животных
CREATE TABLE groups_animals (
	id INT PRIMARY KEY AUTO_INCREMENT,
    agroup VARCHAR(30) NOT NULL 
);

# Создаем таблицу с видами животных с привязкой к группам
CREATE TABLE types_animals (
	id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT NOT NULL,
    atype VARCHAR(30) NOT NULL,
    FOREIGN KEY (group_id)
	REFERENCES groups_animals(id) ON DELETE CASCADE
);

# Создаем таблицу с данными о животных с привязкой к виду
CREATE TABLE animals (
	id INT AUTO_INCREMENT,
    type_id INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    birthday DATE NOT NULL, -- format YYYY-MM-DD
    PRIMARY KEY (id),
    CONSTRAINT animals_fk_type_id FOREIGN KEY (type_id)
	REFERENCES types_animals(id) ON DELETE CASCADE
);

# Создаем таблицу с командами, которые могут выполнять животные
CREATE TABLE commands (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL
);

# Создаем таблицу для связи команд и конкретных животных
CREATE TABLE animals_commands (
    animal_id INT NOT NULL, 
    command_id INT NOT NULL,
    FOREIGN KEY(animal_id) REFERENCES animals(id) ON DELETE CASCADE,
    FOREIGN KEY(command_id) REFERENCES commands(id)
);

