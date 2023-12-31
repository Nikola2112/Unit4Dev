package exsample_petro;



import connection.ConnectionSQL;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

import static connection.ConnectionConfig.CREATE_WORKER_TABLE;
import static connection.ConnectionConfig.TABLE_WORKER;


public class DatabaseInitServicePetroCode {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseInitServicePetroCode.class);

    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();
        try(Connection connection = new ConnectionSQL().getConnection();
            Statement statement = connection.createStatement();
            Scanner s = new Scanner(System.in)) {
            ResultSet resultSet = statement.executeQuery("SELECT table_name\n" +
                    "FROM INFORMATION_SCHEMA.TABLES\n" +
                    "WHERE table_type = 'BASE TABLE'");
            boolean isCreated = false;
            while (resultSet.next()) {
                String tableName = resultSet.getString("table_name");
                logger.info("table name: {}",tableName);
                if(tableName.equals(TABLE_WORKER)) {
                    isCreated = true;
                }
            }
            logger.info("{} created? {}",TABLE_WORKER,isCreated);
            if(!isCreated) {
                logger.info("creating table");
                logger.info("status: {}",statement.execute(CREATE_WORKER_TABLE));
            }
            while(true) {
                logger.info("i need for sql command: ");
                String id = s.nextLine();
                String sql =
                        "select * from consumers where organization_name  like ?";
                PreparedStatement queryStatement = connection.prepareStatement(sql);
                queryStatement.setString(1,id);

                try {
                    resultSet = queryStatement.executeQuery();
                    logger.info("result: {}", resultSetToHashMap(resultSet));
                }catch (org.postgresql.util.PSQLException e){
                    logger.error("error: ",e);
                }
            }
        }catch (org.postgresql.util.PSQLException e){
            logger.error("error: ", e);
        }


    }


    public static List<Map<String,String>> resultSetToHashMap(ResultSet rs) throws SQLException {

        ResultSetMetaData md = rs.getMetaData();
        List<Map<String,String>> resp = new LinkedList<>();
        while (rs.next()) {
            Map<String,String> row = new LinkedHashMap<>();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                row.put(md.getColumnName(i), rs.getString(i));
            }
            resp.add(row);
        }
        return resp;
    }
}