package org.spbu.datausage;

import org.spbu.connector.SQLConnector;
import org.spbu.datastorage.UserDAO;
import org.spbu.datastorage.UserMetricsDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class with methods that execute SQL queries and retrieving raw data
 */

public class DataProvider {
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public void getConnection()
    {
        SQLConnector sqlConnector = new SQLConnector();
        connection = sqlConnector.connect();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public ResultSet getAllData()
    {
        String query = "SELECT * FROM users";

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return resultSet;
    }

    public UserDAO getUserByID(int id)
    {
        String query = "SELECT * FROM users WHERE id = " + id;
        //System.out.println(query);

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        int user_id = 0;
        try {
            user_id = resultSet.getInt("id");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        String name = null;
        try {
            name = resultSet.getString("name");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        String surname = null;
        try {
            surname = resultSet.getString("surname");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        String patronymic = null;
        try {
            patronymic = resultSet.getString("fathname");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        boolean sex = false;
        try {
            sex = resultSet.getBoolean("sex");
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return new UserDAO(user_id, name, surname, patronymic, sex);
    }

    public void insertMetrics(UserMetricsDAO userMetricsDAO){

        String query = "INSERT INTO user_data VALUES (" +
                userMetricsDAO.getUser_id() + ", " +
                userMetricsDAO.getNameVowelCount() + ", " +
                userMetricsDAO.getNameConsonantCount() + ", " +
                userMetricsDAO.getNameSignCount() + ", " +
                userMetricsDAO.getSurnameVowelCount() + ", " +
                userMetricsDAO.getSurnameConsonantCount() + ", " +
                userMetricsDAO.getSurnameSignCount() + ", " +
                userMetricsDAO.getPatronymicVowelCount() + ", " +
                userMetricsDAO.getPatronymicConsonantCount() + ", " +
                userMetricsDAO.getPatronymicSignCount() + ", " +
                userMetricsDAO.isNameDoubleLetter() + ", " +
                userMetricsDAO.isSurnameDoubleLetter() + ", " +
                userMetricsDAO.isPatronymicDoubleLetter() + ")";

        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void updateMetrics(UserMetricsDAO userMetricsDAO) {
        String query = "UPDATE user_data SET " +
                "name_vowel_count = " + userMetricsDAO.getNameVowelCount() + ", " +
                "name_consonant_count = " + userMetricsDAO.getNameConsonantCount() + ", " +
                "name_sign_count = " + userMetricsDAO.getNameSignCount() + ", " +
                "surname_vowel_count = " + userMetricsDAO.getSurnameVowelCount() + ", " +
                "surname_consonant_count = " + userMetricsDAO.getSurnameConsonantCount() + ", " +
                "surname_sign_count = " + userMetricsDAO.getSurnameSignCount() + ", " +
                "patronymic_vowel_count = " + userMetricsDAO.getPatronymicVowelCount() + ", " +
                "patronymic_consonant_count = " + userMetricsDAO.getPatronymicConsonantCount() + ", " +
                "patronymic_sign_count = " + userMetricsDAO.getPatronymicSignCount() + ", " +
                "name_dl_containing = " + userMetricsDAO.isNameDoubleLetter() + ", " +
                "surname_dl_containing = " + userMetricsDAO.isSurnameDoubleLetter() + ", " +
                "patronymic_dl_containing = " + userMetricsDAO.isPatronymicDoubleLetter() +
                "WHERE user_id = " + userMetricsDAO.getUser_id();
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }
}
