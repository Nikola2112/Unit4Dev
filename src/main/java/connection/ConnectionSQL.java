package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionSQL {
    private static  final Logger logger = LoggerFactory.getLogger(ConnectionSQL.class);
    public static Connection getConnection() throws SQLException {
       try{ Class.forName("org.postgresql.Driver");}
       catch (ClassNotFoundException e1){
           logger.error("Do not have driver to do !" + e1);
       }

          return DriverManager.getConnection(ConnectionConfig.URL_JDBC, ConnectionConfig.USER, ConnectionConfig.PASSWORD);
    }
}




