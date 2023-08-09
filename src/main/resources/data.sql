CREATE TABLE students(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    lastname VARCHAR(128) NOT NULL,
    dni INT NOT NULL,
    student_status int,
    student_career int,
    PRIMARY KEY(id)
);

CREATE TABLE types(
    value_id INT NOT NULL,
    meaning VARCHAR(128) NOT NULL,
    type VARCHAR(128) NOT NULL,
    PRIMARY KEY(value_id)
);

CREATE TABLE careers(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE prices(
    id INT NOT NULL AUTO_INCREMENT,
    price_dollar FLOAT NOT NULL,
    price_career FLOAT,
    career_id INT NOT NUll,
    PRIMARY KEY(id),
    FOREIGN KEY(career_id) references careers
);

INSERT INTO students(name, lastname, dni, student_status, student_career)
VALUES  ('Alejandro', 'Maldonado', 30345789, 1, 1),
        ('Ramiro', 'Juarez', 32345781, 2, 1),
        ('Vanesa', 'Redondo', 23347089, 3, 2),
        ('Mariana', 'Crisantemo', 36245709, 1, 3),
        ('Mabel', 'Cruz', 30345789, 1, 1),
        ('Sancho', 'Perez', 32345781, 2, 1),
        ('Martin', 'Trebuco', 23347089, 3, 2),
        ('Patricia', 'Santos', 36245709, 1, 3),
        ('Rocko', 'Luna', 30345789, 1, 1),
        ('Jenifer', 'Wich', 32345781, 2, 1);

INSERT INTO types(value_id, meaning, type)
VALUES  (1, 'Cursando', 'student_status' ),
        (2, 'Titulado', 'student_status' ),
        (3, 'Baja', 'student_status' );

INSERT INTO careers(id,name)
VALUES  (1,'Programación' ),
        (2,'Seguridad informatica' ),
        (3,'Diseño de interfaces' );

INSERT INTO prices(price_dollar,price_career,career_id)
VALUES  (55.00, 11000.00, 1 ),
        (60.00,1200.00,2 ),
        (40.00,8000.00,3);
