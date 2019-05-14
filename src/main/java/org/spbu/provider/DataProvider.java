package org.spbu.provider;

import org.spbu.dao.UserDAO;
import org.spbu.dao.UserMetricsDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
                userMetricsDAO.getNameVowelsInRow() + ", " +
                userMetricsDAO.getNameConsonantInRow() + ", " +
                userMetricsDAO.getSurnameVowelsInRow() + ", " +
                userMetricsDAO.getSurnameConsonantInRow() + ", " +
                userMetricsDAO.getPatronymicVowelsInRow() + ", " +
                userMetricsDAO.getPatronymicConsonantInRow() + ")";

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
                "patronymic_sign_count	 = " + userMetricsDAO.getPatronymicSignCount() + ", " +
                "name_vowels_in_row = " + userMetricsDAO.getNameVowelsInRow() + ", " +
                "name_consonant_count = " + userMetricsDAO.getNameConsonantInRow() + ", " +
                "surname_vowels_in_row = " + userMetricsDAO.getSurnameVowelsInRow() + ", " +
                "surname_consonant_in_row = " + userMetricsDAO.getSurnameConsonantInRow() + ", " +
                "patronymic_vowels_in_row = " + userMetricsDAO.getPatronymicVowelsInRow() + ", " +
                "patronymic_consonant_in_row = " + userMetricsDAO.getPatronymicConsonantInRow() + " " +
                "WHERE user_id = " + userMetricsDAO.getUser_id();
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void insertNewUser(String name, String surname, String patronymic, boolean sex){

        String query;

        if(sex) {
            query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'t\', 0)";
        }
        else{
            query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'f\', 0)";
        }

        try {
            statement.executeQuery(query);
            System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void insertName(String string, boolean sex){
        String query = "";
        if(sex){
            query = "INSERT INTO m_names VALUES (DEFAULT , \'" + string + "\')";
        }
        else{
            query = "INSERT INTO f_names VALUES (DEFAULT, \'" + string + "\')";
        }
        try {
            statement.executeQuery(query);
            System.out.println("updated");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void insertSurname(String string, boolean sex){
        String query = "";
        if(sex){
            query = "INSERT INTO m_surnames VALUES (DEFAULT, \'" + string + "\')";
        }
        else{
            query = "INSERT INTO f_surnames VALUES (DEFAULT, \'" + string + "\')";
        }
        try {
            statement.executeQuery(query);
            System.out.println("updated");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void insertPatronymic(String string, boolean sex){
        String query = "";
        if(sex){
            query = "INSERT INTO m_patronymics VALUES (DEFAULT, \'" + string + "\')";
        }
        else{
            query = "INSERT INTO f_patronymics VALUES (DEFAULT, \'" + string + "\')";
        }
        try {
            statement.executeQuery(query);
            //System.out.println("updated");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public ArrayList<String> getSurnames(boolean sex) throws SQLException {
        ArrayList<String> surnames = new ArrayList<String>();
        String query = "";
        String temp;
        if(sex){
            query = "SELECT * FROM m_surnames";
        }
        else{
            query = "SELECT * FROM f_surnames";
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        resultSet.next();
        while (!resultSet.isAfterLast()){
            temp = resultSet.getString(2);
            surnames.add(temp);
            resultSet.next();
        }
        return surnames;
    }

    public ArrayList<String> getPatronymics(boolean sex) throws SQLException {
        ArrayList<String> patronymics = new ArrayList<String>();
        String query = "";
        String temp;
        if(sex){
            query = "SELECT * FROM m_patronymics";
        }
        else{
            query = "SELECT * FROM f_patronymics";
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        resultSet.next();
        while (!resultSet.isAfterLast()){
            temp = resultSet.getString(2);
            patronymics.add(temp);
            resultSet.next();
        }
        return patronymics;
    }

    public ArrayList<String> getNames(boolean sex) throws SQLException {
        ArrayList<String> names = new ArrayList<String>();
        String query = "";
        String temp;
        if(sex){
            query = "SELECT * FROM m_names";
        }
        else{
            query = "SELECT * FROM f_names";
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
        resultSet.next();
        while (!resultSet.isAfterLast()){
            temp = resultSet.getString(2);
            names.add(temp);
            resultSet.next();
        }
        return names;
    }

    public void insertValidUser(int id, String name, String surname, String patronymic, boolean sex){

        String query;

        if(sex) {
            query = "INSERT INTO valid_users VALUES (" + id + ", \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'t\', NULL, NULL)";
        }
        else{
            query = "INSERT INTO valid_users VALUES (" + id + ", \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'f\', NULL, NULL)";
        }

        try {
            statement.executeQuery(query);
            //System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void insertInvalidUser(int id, String name, String surname, String patronymic, boolean sex){

        String query;

        if(sex) {
            query = "INSERT INTO invalid_users VALUES (" + id + ", \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'t\')";
        }
        else{
            query = "INSERT INTO invalid_users VALUES (" + id + ", \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'f\')";
        }

        try {
            statement.executeQuery(query);
            //System.out.println("Пользователь добавлен");
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public ResultSet getAllValidUsers(){
        String query = "SELECT * FROM valid_users";

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return resultSet;
    }

    public ResultSet getAllInvalidUsers(){
        String query = "SELECT * FROM invalid_users";

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return resultSet;
    }

    public void truncateValidUsers(){
        String query = "TRUNCATE valid_users";

        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public void updateFuzzConsist(int id, int predp){

        String query = "UPDATE users SET fuzz_consistent = " + predp + " WHERE id = " + id;
        try {
            statement.executeQuery(query);
            //System.out.println("updated");
        } catch (SQLException e) {
            //e.printStackTrace();
        }

    }

    public void updateStatConsist(int id, int predp){

        String query = "UPDATE users SET stat_consistent = " + predp + " WHERE id = " + id;
        try {
            statement.executeQuery(query);
            //System.out.println("updated");
        } catch (SQLException e) {
            //e.printStackTrace();
        }

    }

}
