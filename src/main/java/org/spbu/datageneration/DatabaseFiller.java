package org.spbu.datageneration;

import org.spbu.provider.SQLConnector;

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
                query = "INSERT INTO users VALUES (DEFAULT, \'" + femaleNames[random.nextInt(femaleNames.length)] + "\', \'" + femaleSurnames[random.nextInt(femaleSurnames.length)] + "\', \'" + femalePatronymics[random.nextInt(femalePatronymics.length)] + "\', \'f\', \'t\', 0)";
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
                query = "INSERT INTO users VALUES (DEFAULT, \'" + maleNames[random.nextInt(maleNames.length)] + "\', \'" + maleSurnames[random.nextInt(maleSurnames.length)] + "\', \'" + malePatronymics[random.nextInt(malePatronymics.length)] + "\', \'t\', \'t\', 0)";
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

    public void generateErrorData(int amount) {

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


        String name;
        String surname;
        String patronymic;

        Random random = new Random();
        int randomInt;
        int randomInt2;
        String query;

        for (int i = 0; i <= amount; i++) {
            randomInt = random.nextInt(2);

            randomInt2 = random.nextInt(3);

            if (randomInt == 0) {
                name = femaleNames[random.nextInt(femaleNames.length)];
                surname = femaleSurnames[random.nextInt(femaleSurnames.length)];
                patronymic = femalePatronymics[random.nextInt(femalePatronymics.length)];

                switch (randomInt2){
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

                query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'f\', \'f\', 0)";
                try {
                    statement = connection.createStatement();
                    statement.executeQuery(query);
                } catch (SQLException sqlEx) {
                    //sqlEx.printStackTrace();
                }
            } else {

                name = maleNames[random.nextInt(femaleNames.length)];
                surname = maleSurnames[random.nextInt(femaleSurnames.length)];
                patronymic = malePatronymics[random.nextInt(femalePatronymics.length)];

                switch (randomInt2){
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

                query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'t\', \'f\', 0)";
                try {
                    statement = connection.createStatement();
                    statement.executeQuery(query);
                } catch (SQLException sqlEx) {
                    //sqlEx.printStackTrace();
                }
            }
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

                query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'f\', \'f\', 0)";
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

                query = "INSERT INTO users VALUES (DEFAULT, \'" + name + "\', \'" + surname + "\', \'" + patronymic + "\', \'t\', \'f\', 0)";
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
