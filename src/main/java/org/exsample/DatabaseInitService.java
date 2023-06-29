package org.exsample;


import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

public class DatabaseInitService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitService.class);

    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();
        try (Connection connection = new ConnectionSQL().getConnection();
             Statement statement = connection.createStatement()) {

            // Создание таблицы worker, если она не существует
            logger.info("Creating worker table");
            statement.execute("CREATE TABLE IF NOT EXISTS worker (" +
                    "ID SERIAL PRIMARY KEY," +
                    "NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000)," +
                    "BIRTHDAY DATE CHECK (EXTRACT(YEAR FROM BIRTHDAY) > 1900)," +
                    "LEVEL ENUM_LEVEL NOT NULL," +
                    "SALARY INT CHECK (SALARY >= 100 AND SALARY <= 100000)" +
                    ");");

            // Создание таблицы client, если она не существует
            logger.info("Creating client table");
            statement.execute("CREATE TABLE IF NOT EXISTS client (" +
                    "ID SERIAL PRIMARY KEY," +
                    "NAME VARCHAR(1000) NOT NULL CHECK (LENGTH(NAME) >= 2 AND LENGTH(NAME) <= 1000)" +
                    ");");

            // Создание таблицы project, если она не существует
            logger.info("Creating project table");
            statement.execute("CREATE TABLE IF NOT EXISTS project (" +
                    "ID SERIAL PRIMARY KEY," +
                    "CLIENT_ID INT," +
                    "START_DATE DATE," +
                    "FINISH_DATE DATE," +
                    "FOREIGN KEY (CLIENT_ID) REFERENCES client(ID)" +
                    ");");

            // Создание таблицы project_worker, если она не существует
            logger.info("Creating project_worker table");
            statement.execute("CREATE TABLE IF NOT EXISTS project_worker (" +
                    "PROJECT_ID INT," +
                    "WORKER_ID INT," +
                    "PRIMARY KEY (PROJECT_ID, WORKER_ID)," +
                    "FOREIGN KEY (PROJECT_ID) REFERENCES project(ID)," +
                    "FOREIGN KEY (WORKER_ID) REFERENCES worker(ID)" +
                    ");");

            logger.info("База данных инициализирована успешно.");
        } catch (SQLException e) {
            logger.error("Error:", e);
        }
    }

    public static List<Map<String, String>> resultSetToHashMap(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        List<Map<String, String>> resp = new LinkedList<>();
        while (rs.next()) {
            Map<String, String> row = new LinkedHashMap<>();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                row.put(md.getColumnName(i), rs.getString(i));
            }
            resp.add(row);
        }
        return resp;
    }
}
