CREATE TABLE departments (
    id UUID PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE directions (
    id UUID PRIMARY KEY,
    code VARCHAR(255),
    form VARCHAR(255),
    level VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE directions_pass_point (
    direction_id BIGINT,
    pass_point_id BIGINT
);

CREATE TABLE disciplines (
    id UUID PRIMARY KEY,
    semester_id UUID,
    attestation VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE educators (
    department_id UUID PRIMARY KEY,
    id UUID,
    patronymic VARCHAR(255),
    name VARCHAR(255),
    surname VARCHAR(255)
);

CREATE TABLE exams (
    id UUID PRIMARY KEY,
    direction_id UUID,
    name VARCHAR(255)
);

CREATE TABLE individual_achievements (
    id UUID PRIMARY KEY,
    count_points VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE lessons (
    id UUID PRIMARY KEY,
    discipline_id UUID,
    laboratory_work VARCHAR(255),
    lecture VARCHAR(255),
    practice VARCHAR(255)
);

CREATE TABLE pass_points (
    id UUID PRIMARY KEY,
    direction_id UUID,
    category VARCHAR(255),
    avg INT,
    min INT
);

CREATE TABLE profiles (
    id UUID PRIMARY KEY,
    direction_id UUID,
    abbreviation VARCHAR(255),
    description VARCHAR(255),
    form VARCHAR(255),
    institute VARCHAR(255),
    level VARCHAR(255),
    name VARCHAR(255)
);

CREATE TABLE semesters (
    id UUID PRIMARY KEY,
    profile_id UUID,
    name VARCHAR(255)
);
