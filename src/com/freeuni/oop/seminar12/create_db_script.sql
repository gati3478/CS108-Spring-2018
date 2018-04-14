DROP DATABASE IF EXISTS test_db;
CREATE DATABASE test_db;
USE test_db;
-- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<

DROP TABLE IF EXISTS students;
-- remove table if it already exists and start from scratch

CREATE TABLE students
(
  student_id    INTEGER     NOT NULL AUTO_INCREMENT,
  idnumber      VARCHAR(11) NOT NULL,
  firstname     VARCHAR(50) NOT NULL,
  lastname      VARCHAR(50) NOT NULL,
  register_date DATETIME    NOT NULL,
  CONSTRAINT students_pk PRIMARY KEY (student_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

DROP TABLE IF EXISTS courses;
-- remove table if it already exists and start from scratch

CREATE TABLE courses
(
  course_id     INTEGER                       NOT NULL AUTO_INCREMENT,
  course_name   VARCHAR(100)                  NOT NULL,
  course_credit INTEGER                       NOT NULL,
  course_type   ENUM ('ძირითადი', 'არჩევითი') NOT NULL,
  CONSTRAINT courses_pk PRIMARY KEY (course_id)
)
  CHARACTER SET utf8
  COLLATE utf8_general_ci;

DROP TABLE IF EXISTS student_courses;
-- remove table if it already exists and start from scratch

CREATE TABLE student_courses
(
  student_id INTEGER NOT NULL,
  course_id  INTEGER NOT NULL,
  CONSTRAINT student_courses_pk PRIMARY KEY (student_id, course_id),
  CONSTRAINT student_courses_fk1 FOREIGN KEY (student_id)
  REFERENCES students (student_id),
  CONSTRAINT student_courses_fk2 FOREIGN KEY (course_id)
  REFERENCES courses (course_id)
);

INSERT INTO students
(idnumber, firstname, lastname, register_date)
VALUES
  ('01002003004', 'გიორგი', 'კაპანაძე', now()),
  ('01002003005', 'ნანა', 'გაბეხაძე', now()),
  ('01002003006', 'გიორგი', 'კლდიაშვილი', now()),
  ('01002003007', 'ნათია', 'ნათენაძე', now()),
  ('01002003009', 'ნინი', 'სირბილაძე', now());

INSERT INTO courses
(course_name, course_credit, course_type)
VALUES
  ('ობიექტზე ორიენტირებული პროგრამირება', 6, 'ძირითადი'),
  ('შესავალი მონაცემთა ბაზებში', 4, 'ძირითადი'),
  ('კალკულუსი', 5, 'ძირითადი'),
  ('common sense', 8, 'არჩევითი'),
  ('წრფივი ალგებრა', 4, 'არჩევითი');

INSERT INTO student_courses
VALUES
  (1, 1),
  (1, 3),
  (1, 5),
  (2, 2),
  (2, 4),
  (2, 5),
  (3, 1),
  (3, 2),
  (3, 5),
  (4, 2),
  (4, 3),
  (4, 4),
  (5, 1),
  (5, 4),
  (5, 5);
