package org.spbu;

import org.spbu.datageneration.DatabaseFiller;
import org.spbu.service.DatabaseStatisticCounter;
import org.spbu.service.UserService;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        //DatabaseFiller databaseFiller = new DatabaseFiller();

        //databaseFiller.generateData();

        //UserService userService = new UserService();

        //userService.addUser();

        DatabaseStatisticCounter databaseStatisticCounter = new DatabaseStatisticCounter();
        /*
        try {
            databaseStatisticCounter.allDatabaseStatisticCounting(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        //databaseStatisticCounter.averageMetricCounting(true);

        try {
            databaseStatisticCounter.outlinersCounting();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }
}
