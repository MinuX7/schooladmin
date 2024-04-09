create table student (
    id SERIAL PRIMARY KEY,
    photo_file VARCHAR(80),
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    registration_number VARCHAR(10) NOT NULL,
    birth_date DATE,
    father_name VARCHAR(60),
    mother_name VARCHAR(60),
    gender VARCHAR(10) check (gender in ('MALE', 'FEMALE')),
    hobbies TEXT,
    school_id INT NOT NULL,
    CONSTRAINT fk_student_school FOREIGN KEY (school_id) REFERENCES school (id)
);

create table student_studentclass_mapping (
    student_id INT,
    class_id INT,
    PRIMARY KEY(student_id, class_id),
    CONSTRAINT fk_student_class_mapping_student FOREIGN KEY (student_id) REFERENCES student(id),
    CONSTRAINT fk_student_class_mapping_class FOREIGN KEY (class_id) REFERENCES student_class(id)
)