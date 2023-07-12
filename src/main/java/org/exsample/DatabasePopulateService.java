package org.exsample;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DatabasePopulateService {
    private static final Logger logger = LoggerFactory.getLogger(DatabasePopulateService.class);

    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();
        try (Connection connection = new ConnectionSQL().getConnection()) {
            // Вставка данных в таблицу worker
            logger.info("Inserting data into worker table");
            insertDataIntoWorkerTable(connection);

            // Вставка данных в таблицу client
            logger.info("Inserting data into client table");
            insertDataIntoClientTable(connection);

            // Вставка данных в таблицу project
            logger.info("Inserting data into project table");
            insertDataIntoProjectTable(connection);

            // Вставка данных в таблицу project_worker
            logger.info("Inserting data into project_worker table");
            insertDataIntoProjectWorkerTable(connection);

            logger.info("Data insertion completed successfully.");
        } catch (SQLException e) {
            logger.error("Error:", e);
        }
    }

    private static void insertDataIntoWorkerTable(Connection connection) throws SQLException {
        String sql = "INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "John Doe");
            statement.setDate(2, Date.valueOf("1990-05-15"));
            statement.setString(3, "Intermediate");
            statement.setInt(4, 5000);
            statement.addBatch();

            statement.setString(1, "Jane Smith");
            statement.setDate(2, Date.valueOf("1985-08-22"));
            statement.setString(3, "Senior");
            statement.setInt(4, 7500);
            statement.addBatch();

            statement.setString(1, "Mike Johnson");
            statement.setDate(2, Date.valueOf("1993-02-10"));
            statement.setString(3, "Junior");
            statement.setInt(4, 3500);
            statement.addBatch();

            statement.setString(1, "Emily Brown");
            statement.setDate(2, Date.valueOf("1998-11-28"));
            statement.setString(3, "Junior");
            statement.setInt(4, 3000);
            statement.addBatch();

            statement.setString(1, "David Wilson");
            statement.setDate(2, Date.valueOf("1991-09-03"));
            statement.setString(3, "Intermediate");
            statement.setInt(4, 5500);
            statement.addBatch();

            statement.setString(1, "Sarah Davis");
            statement.setDate(2, Date.valueOf("1995-07-19"));
            statement.setString(3, "Junior");
            statement.setInt(4, 3200);
            statement.addBatch();

            statement.setString(1, "Michael Miller");
            statement.setDate(2, Date.valueOf("1988-04-12"));
            statement.setString(3, "Senior");
            statement.setInt(4, 8000);
            statement.addBatch();

            statement.setString(1, "Emma Anderson");
            statement.setDate(2, Date.valueOf("1996-06-27"));
            statement.setString(3, "Junior");
            statement.setInt(4, 2800);
            statement.addBatch();

            statement.setString(1, "Christopher Thompson");
            statement.setDate(2, Date.valueOf("1987-03-07"));
            statement.setString(3, "Senior");
            statement.setInt(4, 7000);
            statement.addBatch();

            statement.setString(1, "Olivia Martinez");
            statement.setDate(2, Date.valueOf("1994-12-31"));
            statement.setString(3, "Intermediate");
            statement.setInt(4, 6000);
            statement.addBatch();

            statement.executeBatch();
        }
    }

    private static void insertDataIntoClientTable(Connection connection) throws SQLException {
        String sql = "INSERT INTO client (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "Petr");
            statement.addBatch();

            statement.setString(1, "Oleksiy");
            statement.addBatch();

            statement.setString(1, "Anton");
            statement.addBatch();

            statement.setString(1, "Dmitriy");
            statement.addBatch();

            statement.setString(1, "Ruslan");
            statement.addBatch();

            statement.executeBatch();
        }
    }

    private static void insertDataIntoProjectTable(Connection connection) throws SQLException {
        String sql = "INSERT INTO project (client_id, start_date, finish_date) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, 2);
            statement.setDate(2, Date.valueOf("2022-05-18"));
            statement.setDate(3, Date.valueOf("2023-01-28"));
            statement.addBatch();

            statement.setInt(1, 6);
            statement.setDate(2, Date.valueOf("2021-02-08"));
            statement.setDate(3, Date.valueOf("2022-08-01"));
            statement.addBatch();

            statement.setInt(1, 4);
            statement.setDate(2, Date.valueOf("2019-06-24"));
            statement.setDate(3, Date.valueOf("2019-07-24"));
            statement.addBatch();

            statement.setInt(1, 3);
            statement.setDate(2, Date.valueOf("2023-04-27"));
            statement.setDate(3, Date.valueOf("2023-06-16"));
            statement.addBatch();

            statement.setInt(1, 5);
            statement.setDate(2, Date.valueOf("2015-12-07"));
            statement.setDate(3, Date.valueOf("2020-10-28"));
            statement.addBatch();

            statement.setInt(1, 2);
            statement.setDate(2, Date.valueOf("2020-07-21"));
            statement.setDate(3, Date.valueOf("2022-01-30"));
            statement.addBatch();

            statement.setInt(1, 5);
            statement.setDate(2, Date.valueOf("2021-09-15"));
            statement.setDate(3, Date.valueOf("2022-05-11"));
            statement.addBatch();

            statement.setInt(1, 6);
            statement.setDate(2, Date.valueOf("2023-02-11"));
            statement.setDate(3, Date.valueOf("2023-04-17"));
            statement.addBatch();

            statement.setInt(1, 2);
            statement.setDate(2, Date.valueOf("2018-05-19"));
            statement.setDate(3, Date.valueOf("2022-01-02"));
            statement.addBatch();

            statement.setInt(1, 4);
            statement.setDate(2, Date.valueOf("2017-01-20"));
            statement.setDate(3, Date.valueOf("2023-03-12"));
            statement.addBatch();

            statement.executeBatch();
        }
    }

    private static void insertDataIntoProjectWorkerTable(Connection connection) throws SQLException {
        String insertQuery = "INSERT INTO project_worker (project_id, worker_id) VALUES (?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {

            statement.setInt(1, 41);
            statement.setInt(2, 2);
            statement.addBatch();

            statement.setInt(1, 41);
            statement.setInt(2, 5);
            statement.addBatch();

            statement.setInt(1, 41);
            statement.setInt(2, 9);
            statement.addBatch();

            statement.setInt(1, 42);
            statement.setInt(2, 1);
            statement.addBatch();

            statement.setInt(1, 42);
            statement.setInt(2, 7);
            statement.addBatch();

            statement.setInt(1, 43);
            statement.setInt(2, 4);
            statement.addBatch();

            statement.setInt(1, 43);
            statement.setInt(2, 6);
            statement.addBatch();

            statement.setInt(1, 43);
            statement.setInt(2, 8);
            statement.addBatch();

            statement.setInt(1, 44);
            statement.setInt(2, 9);
            statement.addBatch();

            statement.setInt(1, 44);
            statement.setInt(2, 1);
            statement.addBatch();

            statement.setInt(1, 45);
            statement.setInt(2, 2);
            statement.addBatch();

            statement.setInt(1, 45);
            statement.setInt(2, 9);
            statement.addBatch();

            statement.setInt(1, 45);
            statement.setInt(2, 2);
            statement.addBatch();

            statement.setInt(1, 45);
            statement.setInt(2, 5);
            statement.addBatch();

            statement.setInt(1, 45);
            statement.setInt(2, 4);
            statement.addBatch();

            statement.setInt(1, 46);
            statement.setInt(2, 3);
            statement.addBatch();

            statement.setInt(1, 46);
            statement.setInt(2, 5);
            statement.addBatch();

            statement.setInt(1, 46);
            statement.setInt(2, 9);
            statement.addBatch();

            statement.setInt(1, 47);
            statement.setInt(2, 10);
            statement.addBatch();

            statement.setInt(1, 48);
            statement.setInt(2, 4);
            statement.addBatch();

            statement.setInt(1, 48);
            statement.setInt(2, 6);
            statement.addBatch();

            statement.setInt(1, 49);
            statement.setInt(2, 2);
            statement.addBatch();

            statement.setInt(1, 49);
            statement.setInt(2, 3);
            statement.addBatch();

            statement.setInt(1, 49);
            statement.setInt(2, 7);
            statement.addBatch();

            statement.setInt(1, 50);
            statement.setInt(2, 2);
            statement.addBatch();

            statement.setInt(1, 50);
            statement.setInt(2, 3);
            statement.addBatch();

            statement.setInt(1, 50);
            statement.setInt(2, 5);
            statement.addBatch();

            statement.setInt(1, 50);
            statement.setInt(2, 10);
            statement.addBatch();

            statement.executeBatch();

            logger.info("Data insertion completed successfully.");

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
