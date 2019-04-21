package org.spbu.provider;

import org.spbu.dao.AverageMetricsDAO;
import org.spbu.dao.UserDAO;
import org.spbu.dao.UserMetricsDAO;

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

    public void getConnection() {
        SQLConnector sqlConnector = new SQLConnector();
        connection = sqlConnector.connect();
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public ResultSet getAllUsers() {
        String query = "SELECT * FROM users";

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getAllUserData() {
        String query = "SELECT * FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return resultSet;
    }

    public UserDAO getUserByID(int id) {
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

    public ResultSet getUsersByName(String name) {
        String query = "SELECT * FROM users WHERE name = '" + name + "'";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getUsersBySurname(String surname) {
        String query = "SELECT * FROM users WHERE surname = '" + surname + "'";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getUsersByPatronymic(String patronymic) {
        String query = "SELECT * FROM users WHERE fathname = '" + patronymic + "'";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        return resultSet;
    }

    public AverageMetricsDAO getAverageMetrics(){
        double[] metrics = new double[9];
        String query = "SELECT * FROM average_metrics WHERE id = 1";
        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            for (int i = 0; i < 9; i++) {
                metrics[i] = resultSet.getDouble(i+1);
            }
        } catch (SQLException e){
            //e.printStackTrace();
        }
        return new AverageMetricsDAO(metrics);

    }

    public void insertUserMetrics(UserMetricsDAO userMetricsDAO){

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

    public void updateUserMetrics(UserMetricsDAO userMetricsDAO) {
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
                "patronymic_dl_containing = " + userMetricsDAO.isPatronymicDoubleLetter() + " " +
                "WHERE user_id = " + userMetricsDAO.getUser_id();
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public AverageMetricsDAO countAverageMetrics() {
        double[] metrics = new double[9];

        String query = "SELECT AVG(name_vowel_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[0] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(name_consonant_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[1] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(name_sign_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[2] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(surname_vowel_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[3] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(surname_consonant_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[4] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(surname_sign_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[5] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(patronymic_vowel_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[6] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(patronymic_consonant_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[7] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        query = "SELECT AVG(patronymic_sign_count) AS avg_metric FROM user_data";

        try {
            resultSet = statement.executeQuery(query);
            resultSet.next();
            metrics[8] = resultSet.getDouble(1);
        } catch (SQLException e){
            //e.printStackTrace();
        }

        AverageMetricsDAO averageMetricsDAO = new AverageMetricsDAO(metrics);

        return averageMetricsDAO;
    }

    public void updateAverageMetrics(AverageMetricsDAO averageMetricsDAO){

        String query = "UPDATE average_metrics SET " +
                "avg_nvc = " + averageMetricsDAO.getAverageNameVowelCount() + ", " +
                "avg_ncc = " + averageMetricsDAO.getAverageNameConsonantCount() + ", " +
                "avg_nsc = " + averageMetricsDAO.getAverageNameSignCount() + ", " +
                "avg_svc = " + averageMetricsDAO.getAverageSurnameVowelCount() + ", " +
                "avg_scc = " + averageMetricsDAO.getAverageSurnameConsonantCount() + ", " +
                "avg_ssc = " + averageMetricsDAO.getAverageSurnameSignCount() + ", " +
                "avg_pvc = " + averageMetricsDAO.getAveragePatronymicVowelCount() + ", " +
                "avg_pcc = " + averageMetricsDAO.getAveragePatronymicConsonantCount() + ", " +
                "avg_psc = " + averageMetricsDAO.getAveragePatronymicSignCount() + " " +
                "WHERE id = 1";
        try {
            statement.executeQuery(query);
            System.out.println("Average metrics updated");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAverageMetrics(AverageMetricsDAO averageMetricsDAO){

        String query = "INSERT INTO average_metrics VALUES (1, " +
                averageMetricsDAO.getAverageNameVowelCount() + ", " +
                averageMetricsDAO.getAverageNameConsonantCount() + ", " +
                averageMetricsDAO.getAverageNameSignCount() + ", " +
                averageMetricsDAO.getAverageSurnameVowelCount() + ", " +
                averageMetricsDAO.getAverageSurnameConsonantCount() + ", " +
                averageMetricsDAO.getAverageSurnameSignCount() + ", " +
                averageMetricsDAO.getAveragePatronymicVowelCount() + ", " +
                averageMetricsDAO.getAveragePatronymicConsonantCount() + ", " +
                averageMetricsDAO.getAveragePatronymicSignCount() + ")";
        try {
            statement.executeQuery(query);
            System.out.println("Average metrics inserted");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewUser(String name, String surname, String patronymic, boolean sex ){

        String query;

        if(sex) {
            query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'t\')";
        }
        else{
            query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'f\')";
        }

        try {
            statement.executeQuery(query);
            System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void countUserConsistency(UserMetricsDAO userMetricsDAO){

        String query;

        if (userMetricsDAO.isMultipleLetter()){
            query = "INSERT INTO consistency_labels VALUES(" +
                    userMetricsDAO.getUser_id() + ", \'f\')";
        }
        else{
            query = "INSERT INTO consistency_labels VALUES(" +
                    userMetricsDAO.getUser_id() + ", \'t\')";
        }

        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
