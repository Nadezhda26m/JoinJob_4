# Создаем базу данных friendsman
DROP DATABASE IF EXISTS friendsman;
CREATE DATABASE friendsman;
USE friendsman;

# Создаем таблицу с группами животных
CREATE TABLE animals (
	id INT PRIMARY KEY AUTO_INCREMENT,
    agroup VARCHAR(30) NOT NULL 
);

# Создаем таблицу с видами животных с привязкой к группам
CREATE TABLE types_animals (
	id INT PRIMARY KEY AUTO_INCREMENT,
    group_id INT NOT NULL,
    atype VARCHAR(30) NOT NULL,
    FOREIGN KEY (group_id)
	REFERENCES animals(id) ON DELETE CASCADE
);

# Создаем таблицы dogs, cats, hamsters, horses, camels, donkeys с привязкой к виду
CREATE TABLE dogs (
	id INT AUTO_INCREMENT,
    type_id INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    birthday DATE NOT NULL, -- format YYYY-MM-DD
    PRIMARY KEY (id),
    CONSTRAINT dogs_fk_type_id FOREIGN KEY (type_id)
	REFERENCES types_animals(id) ON DELETE CASCADE
);

CREATE TABLE cats (
	id INT AUTO_INCREMENT,
    type_id INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    birthday DATE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT cats_fk_type_id FOREIGN KEY (type_id)
	REFERENCES types_animals(id) ON DELETE CASCADE
);

CREATE TABLE hamsters (
	id INT AUTO_INCREMENT,
    type_id INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    birthday DATE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT hamsters_fk_type_id FOREIGN KEY (type_id)
	REFERENCES types_animals(id) ON DELETE CASCADE
);

CREATE TABLE horses (
	id INT AUTO_INCREMENT,
    type_id INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    birthday DATE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT horses_fk_type_id FOREIGN KEY (type_id)
	REFERENCES types_animals(id) ON DELETE CASCADE
);

CREATE TABLE camels (
	id INT AUTO_INCREMENT,
    type_id INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    birthday DATE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT camels_fk_type_id FOREIGN KEY (type_id) 
	REFERENCES types_animals(id) ON DELETE CASCADE
);

CREATE TABLE donkeys (
	id INT AUTO_INCREMENT,
    type_id INT NOT NULL,
    name VARCHAR(25) NOT NULL,
    birthday DATE NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT donkeys_fk_type_id FOREIGN KEY (type_id)
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
    animal_type INT NOT NULL,
    command_id INT,
    FOREIGN KEY(command_id) REFERENCES commands(id) ON DELETE CASCADE,
    PRIMARY KEY(animal_id, animal_type, command_id)
);

