package org.spbu.datageneration;

import org.spbu.provider.DataProvider;
import org.spbu.provider.SQLConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Class that generates DB
 */

public class DatabaseFiller {

    private DataStorage data;
    private Connection connection;
    private DataProvider dataProviderInput;
    private DataProvider dataProviderOutput;

    public void initializeDictionaries(){
        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();
        data = new DataStorage();

        data.initializeFemaleNames();
        data.initializeFemalePatronymics();
        data.initializeFemaleSurnames();

        data.initializeMaleNames();
        data.initializeMalePatronymics();
        data.initializeMaleSurnames();

        String[] femaleNames = data.getFemaleNames();
        String[] femaleSurnames = data.getFemaleSurnames();
        String[] femalePatronymics = data.getFemalePatronymics();

        String[] maleNames = data.getMaleNames();
        String[] maleSurnames = data.getMaleSurnames();
        String[] malePatronymics = data.getMalePatronymics();

        System.out.println(femaleNames[0]);
        System.out.println(femaleNames[femaleNames.length - 1]);

        for (int i = 0; i < femaleNames.length; i++) {
            dataProviderInput.insertName(femaleNames[i], false);
        }
        for (int i = 0; i < maleNames.length; i++) {
            dataProviderInput.insertName(maleNames[i], true);
        }
        for (int i = 0; i < femaleSurnames.length; i++) {
            dataProviderInput.insertSurname(femaleSurnames[i], false);
        }
        for (int i = 0; i < maleSurnames.length; i++) {
            dataProviderInput.insertSurname(maleSurnames[i], true);
        }
        for (int i = 0; i < femalePatronymics.length; i++) {
            dataProviderInput.insertPatronymic(femalePatronymics[i], false);
        }
        for (int i = 0; i < malePatronymics.length; i++) {
            dataProviderInput.insertPatronymic(malePatronymics[i], true);
        }

    }

    public void generateData(int amount){

        Statement statement;
        SQLConnector sqlconnector = new SQLConnector();
        connection = sqlconnector.connect();
        data = new DataStorage();

        data.initializeFemaleNames();
        data.initializeFemalePatronymics();
        data.initializeFemaleSurnames();

        data.initializeMaleNames();
        data.initializeMalePatronymics();
        data.initializeMaleSurnames();

        String[] femaleNames = data.getFemaleNames();
        String[] femaleSurnames = data.getFemaleSurnames();
        String[] femalePatronymics = data.getFemalePatronymics();

        String[] maleNames = data.getMaleNames();
        String[] maleSurnames = data.getMaleSurnames();
        String[] malePatronymics = data.getMalePatronymics();

        Random random = new Random();
        int randomInt;
        String query;

        for (int i = 0; i<=amount; i++)
        {
            randomInt = random.nextInt(2);

            if (randomInt == 0)
            {
                query = "INSERT INTO users VALUES (DEFAULT, \'" + femaleNames[random.nextInt(femaleNames.length)] + "\', \'" + femaleSurnames[random.nextInt(femaleSurnames.length)] + "\', \'" + femalePatronymics[random.nextInt(femalePatronymics.length)] + "\', \'f\', NULL, NULL)";
                try {
                    statement = connection.createStatement();
                    statement.executeQuery(query);
                }
                catch (SQLException sqlEx){
                    //sqlEx.printStackTrace();
                }
            }
            else
            {
                query = "INSERT INTO users VALUES (DEFAULT, \'" + maleNames[random.nextInt(maleNames.length)] + "\', \'" + maleSurnames[random.nextInt(maleSurnames.length)] + "\', \'" + malePatronymics[random.nextInt(malePatronymics.length)] + "\', \'t\', NULL, NULL)";
                try {
                    statement = connection.createStatement();
                    statement.executeQuery(query);
                }
                catch (SQLException sqlEx){
                    //sqlEx.printStackTrace();
                }
            }
        }
    }

    public void generateErrorData() throws SQLException {
        Statement statement;
        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();
        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();
        ResultSet resultSet = dataProviderInput.getAllUsers();
        resultSet.next();
        Random random = new Random();
        int randomInt = random.nextInt(3);
        int id = 0;
        String name;
        String surname;
        String patronymic;
        boolean sex = false;

        while(!resultSet.isAfterLast()){
            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            patronymic = resultSet.getString("patronymic");
            sex = resultSet.getBoolean("sex");
            switch(randomInt){
                case 0:
                    name = ErrorMaker.deformString(name);
                    break;
                case 1:
                    surname = ErrorMaker.deformString(surname);
                    break;
                case 2:
                    patronymic = ErrorMaker.deformString(patronymic);
                    break;

            }
            //System.out.println("");
            dataProviderOutput.insertInvalidUser(id, name, surname, patronymic, sex);
            resultSet.next();
        }
    }

    public void generateRandomData(int amount){
        Statement statement;
        SQLConnector sqlconnector = new SQLConnector();
        connection = sqlconnector.connect();

        String name;
        String surname;
        String patronymic;

        Random random = new Random();
        int randomInt;

        String query;

        for (int i = 0; i <= amount; i++) {
            randomInt = random.nextInt(2);

            if (randomInt == 0) {
                name = ErrorMaker.randomString();
                surname = ErrorMaker.randomString();
                patronymic = ErrorMaker.randomString();

                query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'f\', NULL, NULL)";
                try {
                    statement = connection.createStatement();
                    statement.executeQuery(query);
                } catch (SQLException sqlEx) {
                    //sqlEx.printStackTrace();
                }
            } else {

                name = ErrorMaker.randomString();
                surname = ErrorMaker.randomString();
                patronymic = ErrorMaker.randomString();

                query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'t\', NULL, NULL)";
                try {
                    statement = connection.createStatement();
                    statement.executeQuery(query);
                } catch (SQLException sqlEx) {
                    //sqlEx.printStackTrace();
                }
            }
        }
    }


}
