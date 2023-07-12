package org.exsample;



import java.sql.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DatabaseInitService {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(ConnectionConfig.URL_JDBC, ConnectionConfig.USER, ConnectionConfig.PASSWORD)) {
            // Проверка наличия таблицы "worker"
            if (!tableExists(connection, ConnectionConfig.TABLE_WORKER)) {
                createWorkerTable(connection);
            } else {
                System.out.println("Таблица 'worker' уже существует");
            }

            // Проверка наличия таблицы "client"
            if (!tableExists(connection, ConnectionConfig.TABLE_CLIENT)) {
                createClientTable(connection);
            } else {
                System.out.println("Таблица 'client' уже существует");
            }

            // Проверка наличия таблицы "project"
            if (!tableExists(connection, ConnectionConfig.TABLE_PROJECT)) {
                createProjectTable(connection);
            } else {
                System.out.println("Таблица 'project' уже существует");
            }

            // Проверка наличия таблицы "project_worker"
            if (!tableExists(connection, ConnectionConfig.TABLE_PROJECT_WORKER)) {
                createProjectWorkerTable(connection);
            } else {
                System.out.println("Таблица 'project_worker' уже существует");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean tableExists(Connection connection, String tableName) throws SQLException {
        String checkTableQuery = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ? AND table_schema = 'public')";
        try (PreparedStatement statement = connection.prepareStatement(checkTableQuery)) {
            statement.setString(1, tableName);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getBoolean(1);
            }
        }
    }

    private static void createWorkerTable(Connection connection) throws SQLException {
        String createTableQuery = ConnectionConfig.CREATE_WORKER_TABLE;
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
            System.out.println("Таблица 'worker' создана");
        }
    }

    private static void createClientTable(Connection connection) throws SQLException {
        String createTableQuery = ConnectionConfig.CREATE_CLIENT_TABLE;
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
            System.out.println("Таблица 'client' создана");
        }
    }

    private static void createProjectTable(Connection connection) throws SQLException {
        String createTableQuery = ConnectionConfig.CREATE_PROJECT_TABLE;
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
            System.out.println("Таблица 'project' создана");
        }
    }

    private static void createProjectWorkerTable(Connection connection) throws SQLException {
        String createTableQuery = ConnectionConfig.CREATE_PROJECT_WORKER_TABLE;
        try (PreparedStatement statement = connection.prepareStatement(createTableQuery)) {
            statement.executeUpdate();
            System.out.println("Таблица 'project_worker' создана");
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
