package org.spbu.datausage;

import org.spbu.connector.SQLConnector;
import org.spbu.datastorage.UserData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataGetter {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public UserData getUserByID(int id)
    {
        SQLConnector sqlConnector = new SQLConnector();
        connection = sqlConnector.connect();

        String query = "SELECT * FROM users WHERE id = " + id;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        }
        catch (SQLException sqlEx){
            //sqlEx.printStackTrace();
        }

        int user_id = 0;
        try {
            user_id = resultSet.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String name = null;
        try {
            name = resultSet.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String surname = null;
        try {
            surname = resultSet.getString("surname");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String patronymic = null;
        try {
            patronymic = resultSet.getString("fathname");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        boolean sex = false;
        try {
            sex = resultSet.getBoolean("sex");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new UserData(user_id, name, surname, patronymic, sex);
    }
}
