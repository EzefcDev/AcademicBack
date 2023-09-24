CREATE TABLE students(id INT NOT NULL AUTO_INCREMENT, name VARCHAR(128) NOT NULL, lastname VARCHAR(128) NOT NULL, dni INT NOT NULL, student_status int, student_career int, delete_student date default null, PRIMARY KEY(id));

CREATE TABLE types(value_id INT NOT NULL, meaning VARCHAR(128) NOT NULL, type VARCHAR(128) NOT NULL, PRIMARY KEY(value_id));

INSERT INTO students(name, lastname, dni, student_status, student_career, delete_student)
VALUES  ('Alejando', 'Maldonado', 30345789, 1, 1, null),
        ('Ramiro', 'Juarez', 32345781, 2, 1, null),
        ('Vanesa', 'Redondo', 23347089, 3, 2, null),
        ('Mariana', 'Crisantemo', 36245709, 1, 3, null),
        ('Alejando', 'Maldonado', 30345789, 1, 1, null),
        ('Ramiro', 'Juarez', 32345781, 2, 1, null),
        ('Vanesa', 'Redondo', 23347089, 3, 2, null),
        ('Mariana', 'Crisantemo', 36245709, 1, 3, null),
        ('Alejando', 'Maldonado', 30345789, 1, 1, null),
        ('Ramiro', 'Juarez', 32345781, 2, 1, null),
        ('Vanesa', 'Redondo', 23347089, 3, 2, null),
        ('Mariana', 'Cinceló', 36245709, 1, 3, null),
        ('Alejando', 'Maldonado', 30345789, 1, 1, null),
        ('Ramiro', 'Juarez', 32345781, 2, 1, null),
        ('Vanesa', 'Redondo', 23347089, 3, 2, null),
        ('Mariana', 'Cinceló', 36245709, 1, 3, null),
        ('Alejando', 'Maldonado', 30345789, 1, 1, null),
        ('Ramiro', 'Juarez', 32345781, 2, 1, null),
        ('Vanesa', 'Redondo', 23347089, 3, 2, null),
        ('Mariana', 'Crisantemo', 36245709, 1, 3, null),
        ('Alejando', 'Maldonado', 30345789, 1, 1, null),
        ('Ramiro', 'Juarez', 32345781, 2, 1, null),
        ('Vanesa', 'Redondo', 23347089, 3, 2, null),
        ('Mariana', 'Crisantemo', 36245709, 1, 3, null),
        ('Alejando', 'Maldonado', 30345789, 1, 1, null);

INSERT INTO types(value_id, meaning, type)
VALUES  (1, 'Cursando', 'student_status' ),
        (2, 'Titulado', 'student_status' ),
        (3, 'Baja', 'student_status' );
