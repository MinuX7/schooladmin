create table student_class (
    id SERIAL PRIMARY KEY,
    grade SMALLINT NOT NULL ,
    label CHAR(1),
    school_id INT NOT NULL,
    CONSTRAINT fk_class_school FOREIGN KEY (school_id) REFERENCES school (id),
    CONSTRAINT unique_grade_label UNIQUE NULLS NOT DISTINCT (grade, label)
);

create table course(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL ,
    description TEXT,
    teacher_id INT NOT NULL,
    class_id INT NOT NULL,
    CONSTRAINT fk_course_teacher FOREIGN KEY (teacher_id) REFERENCES teacher (id),
    CONSTRAINT fk_course_class FOREIGN KEY (class_id) REFERENCES student_class (id)
)
