CREATE TABLE worker (
                        ID SERIAL PRIMARY KEY,
                        NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000),
                        BIRTHDAY DATE CHECK (EXTRACT(YEAR FROM BIRTHDAY) > 1900),
                        Level ENUM_LEVEL NOT NULL,
                        SALARY INT CHECK (SALARY >= 100 AND SALARY <= 100000)
);
CREATE TABLE client (
                        ID SERIAL PRIMARY KEY,
                        NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000)
);
CREATE TYPE ENUM_LEVEL AS ENUM (
    'Trainee',
    'Junior',
    'Middle',
    'Senior'
);
CREATE TABLE project (
                         ID SERIAL PRIMARY KEY,
                         CLIENT_ID INT,
                         START_DATE DATE,
                         FINISH_DATE DATE,
                         FOREIGN KEY (CLIENT_ID) REFERENCES client(ID)
);
CREATE TABLE project_worker (
                                PROJECT_ID INT,
                                WORKER_ID INT,
                                PRIMARY KEY (PROJECT_ID, WORKER_ID),
                                FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),
                                FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)
);

