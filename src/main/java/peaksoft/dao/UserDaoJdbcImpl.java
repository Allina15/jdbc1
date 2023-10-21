package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Config;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = "create table if not exists users("+
                "id serial primary key ," +
                "name varchar (50), " +
                "last_name varchar(50), "+
                "age int)";
        try(Connection connection = Config.getConnection();
            Statement statement = connection.createStatement();){
            statement.executeUpdate(sql);
            System.out.println("Created");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
         String sql = "drop table if exists users";
         try(Connection connection = Config.getConnection();
         Statement statement = connection.createStatement();){
         statement.executeUpdate(sql);
         System.out.println("table dropped");
     }catch (SQLException e){
         throw  new RuntimeException(e);
     }
    }

    public void saveUser(String name, String lastName, byte age) {
    String sql = "insert into users(name, last_name, age)"+"values (?,?,?)";
    try(Connection connection = Config.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,lastName);
        preparedStatement.setInt(3,age);
        preparedStatement.executeUpdate();
        System.out.println("User saved");
        }catch (SQLException e){
        throw new RuntimeException(e);
    }
    }

    public void removeUserById(long id) {
            String sql = "delete from users where id = ?";
        try(
            Connection connection = Config.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("user with id "+ id+ "removed successfully");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User>users = new ArrayList<>();
        String sql = "select * from users";
        try (Connection connection = Config.getConnection();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                users.add(new User(resultSet.getString("name"),
                                   resultSet.getString("last_name"),
                                   resultSet.getByte("age")));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "truncate table users";
        try(Connection connection = Config.getConnection();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("User table cleaned successfully.");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}