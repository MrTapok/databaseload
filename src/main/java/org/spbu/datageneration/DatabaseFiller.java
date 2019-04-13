package org.spbu.datageneration;

import org.spbu.connector.SQLConnector;
import org.spbu.datageneration.DataStorage;

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

    public void generateData(){

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

        for (int i = 0; i<=50000; i++)
        {
            randomInt = random.nextInt(2);

            if (randomInt == 0)
            {
                query = "INSERT INTO users VALUES (" + i + ", \'" + femaleNames[random.nextInt(femaleNames.length)] + "\', \'" + femaleSurnames[random.nextInt(femaleSurnames.length)] + "\', \'" + femalePatronymics[random.nextInt(femalePatronymics.length)] + "\', \'f\')";
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
                query = "INSERT INTO users VALUES (" + i + ", \'" + maleNames[random.nextInt(maleNames.length)] + "\', \'" + maleSurnames[random.nextInt(maleSurnames.length)] + "\', \'" + malePatronymics[random.nextInt(malePatronymics.length)] + "\', \'t\')";
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



}
