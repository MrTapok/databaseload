package org.spbu.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.spbu.dao.UserDAO;
import org.spbu.dao.UserMetricsDAO;
import org.spbu.provider.DataProvider;

/**
 * Class containing methods for statistic count
 */
public class DatabaseStatisticCounter {

    private DataProvider dataProviderInput;
    private DataProvider dataProviderOutput;
    private ResultSet resultSet;

    /**
     * Method, that counts average value of vowels, consonants and signs in person's ID
     * @throws SQLException
     */

    public void allDatabaseStatisticCounting(Boolean update) throws SQLException {

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();

        //List<UserMetricsDAO> metrics = new ArrayList<UserMetricsDAO>();

        resultSet = dataProviderInput.getAllUsers();
        resultSet.next();
        int id = 0;
        String name = null;
        String surname = null;
        String patronymic = null;
        boolean sex = false;

        while(!resultSet.isAfterLast())
        {
            try{
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                patronymic = resultSet.getString("patronymic");
                sex = resultSet.getBoolean("sex");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            UserDAO userDAO = new UserDAO(id, name, surname, patronymic, sex);

            UserMetricsDAO userMetricsDAO = new UserMetricsDAO(userDAO);

            if(update){
                dataProviderOutput.updateUserMetrics(userMetricsDAO);
            }
            else {
                dataProviderOutput.insertUserMetrics(userMetricsDAO);
            }
            //metrics.add(userMetricsDAO);
            resultSet.next();
        }
    }

}