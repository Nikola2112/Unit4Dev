package connection;

public class ConnectionConfig {

    private ConnectionConfig(){}
    public static final String URL_JDBC = "jdbc:postgresql://localhost:5432/task";
    public static final String USER = "myuser";
    public static final String PASSWORD = "mysecretpassword";

    public static final String TABLE_WORKER = "worker";
    public static final String TABLE_CLIENT = "client";
    public static final String TABLE_PROJECT = "project";
    public static final String TABLE_PROJECT_WORKER = "project_worker";




    public static final String CREATE_ENUM_TYPE = "CREATE TYPE ENUM_LEVEL AS ENUM (\n" +
            "    'Trainee',\n" +
            "    'Junior',\n" +
            "    'Middle',\n" +
            "    'Senior'\n" +
            ");";

    public static final String CREATE_PROJECT_TABLE = "CREATE TABLE project (\n" +
            "    ID SERIAL PRIMARY KEY,\n" +
            "    CLIENT_ID INT,\n" +
            "    START_DATE DATE,\n" +
            "    FINISH_DATE DATE,\n" +
            "    FOREIGN KEY (CLIENT_ID) REFERENCES client(ID)\n" +
            ");";

    public static final String CREATE_PROJECT_WORKER_TABLE = "CREATE TABLE project_worker (\n" +
            "    PROJECT_ID INT,\n" +
            "    WORKER_ID INT,\n" +
            "    PRIMARY KEY (PROJECT_ID, WORKER_ID),\n" +
            "    FOREIGN KEY (PROJECT_ID) REFERENCES project(ID),\n" +
            "    FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)\n" +
            ");";
    public static final String CREATE_CLIENT_TABLE = "CREATE TABLE client (\n" +
            "    ID SERIAL PRIMARY KEY,\n" +
            "    NAME TEXT,\n" +
            "    EMAIL TEXT\n" +
            ");";

    public static final String CREATE_WORKER_TABLE = "CREATE TABLE worker (\n" +
            "    ID SERIAL PRIMARY KEY,\n" +
            "    NAME TEXT,\n" +
            "    LEVEL ENUM_LEVEL\n" +
            ");";
}