package org.exsample;

import ClassForQuery.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseQueryService.class);

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        try (Connection connection = new ConnectionSQL().getConnection();
             Statement statement = connection.createStatement()) {
            List<MaxProjectCountClient> result = new ArrayList<>();
            String query = "SELECT client.ID, client.NAME, COUNT(project.ID) AS project_count " +
                    "FROM client " +
                    "JOIN project ON client.ID = project.CLIENT_ID " +
                    "GROUP BY client.ID, client.NAME " +
                    "HAVING COUNT(project.ID) = (" +
                    "   SELECT MAX(project_count) " +
                    "   FROM (" +
                    "       SELECT COUNT(project.ID) AS project_count " +
                    "       FROM client " +
                    "       JOIN project ON client.ID = project.CLIENT_ID " +
                    "       GROUP BY client.ID" +
                    "   ) AS project_counts" +
                    ")";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int projectCount = resultSet.getInt("project_count");
                result.add(new MaxProjectCountClient(name, projectCount));
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error executing findMaxProjectsClient:", e);
            return null;
        }
    }

    public List<ProjectDuration> findProjectsByDuration() {
        try (Connection connection = new ConnectionSQL().getConnection();
             Statement statement = connection.createStatement()) {
            List<ProjectDuration> result = new ArrayList<>();
            String query = "SELECT project.ID, project.START_DATE, project.FINISH_DATE, " +
                    "EXTRACT(MONTH FROM project.FINISH_DATE) - EXTRACT(MONTH FROM project.START_DATE) AS duration_months " +
                    "FROM project " +
                    "WHERE EXTRACT(MONTH FROM project.FINISH_DATE) - EXTRACT(MONTH FROM project.START_DATE) = (" +
                    "   SELECT MAX(duration_months) " +
                    "   FROM (" +
                    "       SELECT EXTRACT(MONTH FROM project.FINISH_DATE) - EXTRACT(MONTH FROM project.START_DATE) AS duration_months " +
                    "       FROM project" +
                    "   ) AS project_durations" +
                    ")";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int projectId = resultSet.getInt("ID");
                Date startDate = resultSet.getDate("START_DATE");
                Date finishDate = resultSet.getDate("FINISH_DATE");
                int durationMonths = resultSet.getInt("duration_months");
                result.add(new ProjectDuration(projectId, startDate, finishDate, durationMonths));
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error executing findProjectsByDuration:", e);
            return null;
        }
    }

    public List<Worker> findMaxSalaryWorker() {
        try (Connection connection = new ConnectionSQL().getConnection();
             Statement statement = connection.createStatement()) {
            List<Worker> result = new ArrayList<>();
            String query = "SELECT NAME, SALARY " +
                    "FROM worker " +
                    "WHERE SALARY = (" +
                    "   SELECT MAX(SALARY) " +
                    "   FROM worker" +
                    ")";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                int salary = resultSet.getInt("SALARY");
                result.add(new Worker(name, salary));
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error executing findMaxSalaryWorker:", e);
            return null;
        }
    }

    public List<ExtremeBirthdayWorker> findExtremeBirthdays() {
        try (Connection connection = new ConnectionSQL().getConnection();
             Statement statement = connection.createStatement()) {
            List<ExtremeBirthdayWorker> result = new ArrayList<>();
            String query = "SELECT 'YOUNGEST' AS TYPE, NAME, BIRTHDAY " +
                    "FROM worker " +
                    "WHERE BIRTHDAY = (" +
                    "   SELECT MIN(BIRTHDAY) " +
                    "   FROM worker" +
                    ") " +
                    "UNION " +
                    "SELECT 'OLDEST' AS TYPE, NAME, BIRTHDAY " +
                    "FROM worker " +
                    "WHERE BIRTHDAY = (" +
                    "   SELECT MAX(BIRTHDAY) " +
                    "   FROM worker" +
                    ")";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String type = resultSet.getString("TYPE");
                String name = resultSet.getString("NAME");
                Date birthday = resultSet.getDate("BIRTHDAY");
                result.add(new ExtremeBirthdayWorker(type, name, birthday));
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error executing findExtremeBirthdays:", e);
            return null;
        }
    }

    public List<ProjectCost> calculateProjectCost() {
        try (Connection connection = new ConnectionSQL().getConnection();
             Statement statement = connection.createStatement()) {
            List<ProjectCost> result = new ArrayList<>();
            String query = "SELECT project.ID AS PROJECT_ID, " +
                    "SUM(worker.SALARY) * (DATE_PART('month', project.FINISH_DATE) - DATE_PART('month', project.START_DATE)) AS PROJECT_COST " +
                    "FROM project " +
                    "JOIN project_worker ON project.ID = project_worker.PROJECT_ID " +
                    "JOIN worker ON project_worker.WORKER_ID = worker.ID " +
                    "GROUP BY project.ID, project.START_DATE, project.FINISH_DATE";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int projectId = resultSet.getInt("PROJECT_ID");
                double projectCost = resultSet.getDouble("PROJECT_COST");
                result.add(new ProjectCost(projectId, projectCost));
            }
            return result;
        } catch (SQLException e) {
            logger.error("Error executing calculateProjectCost:", e);
            return null;
        }
    }
}
