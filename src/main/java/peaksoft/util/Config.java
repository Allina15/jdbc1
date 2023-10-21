package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {
    private final static String url = "jdbc:postgresql://localhost:5432/postgres";
    private final static String username = "postgres";
    private final static String password = "1234";

    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection success");
            return connection;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
