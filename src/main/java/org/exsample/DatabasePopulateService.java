package org.exsample;
import connection.ConnectionSQL;
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
        try (Connection connection = new ConnectionSQL().getConnection();
             Statement statement = connection.createStatement()) {
            // Вставка данных в таблицу worker
            logger.info("Inserting data into worker table");
            statement.executeUpdate("INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES " +
                    "('John Doe', '1990-05-15', 'Intermediate', 5000), " +
                    "('Jane Smith', '1985-08-22', 'Senior', 7500), " +
                    "('Mike Johnson', '1993-02-10', 'Junior', 3500), " +
                    "('Emily Brown', '1998-11-28', 'Junior', 3000), " +
                    "('David Wilson', '1991-09-03', 'Intermediate', 5500), " +
                    "('Sarah Davis', '1995-07-19', 'Junior', 3200), " +
                    "('Michael Miller', '1988-04-12', 'Senior', 8000), " +
                    "('Emma Anderson', '1996-06-27', 'Junior', 2800), " +
                    "('Christopher Thompson', '1987-03-07', 'Senior', 7000), " +
                    "('Olivia Martinez', '1994-12-31', 'Intermediate', 6000)" +
                    "ON CONFLICT DO NOTHING;");

            // Вставка данных в таблицу client
            logger.info("Inserting data into client table");
            statement.executeUpdate("INSERT INTO client (name) VALUES " +
                    "('Petr'), " +
                    "('Oleksiy'), " +
                    "('Anton'), " +
                    "('Dmitriy'), " +
                    "('Ruslan')" +
                    "ON CONFLICT DO NOTHING;");

            // Вставка данных в таблицу project
            logger.info("Inserting data into project table");
            statement.executeUpdate("INSERT INTO project (client_id, start_date, finish_date) VALUES " +
                    "(2, '2022-05-18', '2023-01-28'), " +
                    "(6, '2021-02-08', '2022-08-01'), " +
                    "(4, '2019-06-24', '2019-07-24'), " +
                    "(3, '2023-04-27', '2023-06-16'), " +
                    "(5, '2015-12-07', '2020-10-28'), " +
                    "(2, '2020-07-21', '2022-01-30'), " +
                    "(5, '2021-09-15', '2022-05-11'), " +
                    "(6, '2023-02-11', '2023-04-17'), " +
                    "(2, '2018-05-19', '2022-01-02'), " +
                    "(4, '2017-01-20', '2023-03-12')" +
                    "ON CONFLICT DO NOTHING;");

            // Вставка данных в таблицу project_worker
            logger.info("Inserting data into project_worker table");
            statement.executeUpdate("INSERT INTO project_worker (project_id, worker_id) VALUES " +
                    "(41, 2), " +
                    "(41, 5), " +
                    "(41, 9), " +
                    "(42, 1), " +
                    "(42, 7), " +
                    "(43, 4), " +
                    "(43, 6), " +
                    "(43, 8), " +
                    "(44, 9), " +
                    "(44, 1), " +
                    "(45, 3), " +
                    "(45, 8), " +
                    "(45, 1), " +
                    "(45, 6), " +
                    "(45, 2), " +
                    "(46, 3), " +
                    "(46, 5), " +
                    "(46, 9), " +
                    "(47, 10), " +
                    "(48, 4), " +
                    "(48, 6), " +
                    "(49, 2), " +
                    "(49, 3), " +
                    "(49, 7), " +
                    "(50, 2), " +
                    "(50, 3), " +
                    "(50, 5), " +
                    "(50, 10)" +
                    "ON CONFLICT DO NOTHING;");

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
