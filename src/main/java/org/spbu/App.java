package org.spbu;

import org.spbu.datausage.DatabaseFiller;
import org.spbu.datausage.DatabaseStatisticCounter;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        //DatabaseFiller databaseFiller = new DatabaseFiller();
        //databaseFiller.generateData();

        DatabaseStatisticCounter databaseStatisticCounter = new DatabaseStatisticCounter();
        try {
            databaseStatisticCounter.averageLetterCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
