package org.spbu;

import org.spbu.service.DatabaseStatisticCounter;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        DatabaseStatisticCounter databaseStatisticCounter = new DatabaseStatisticCounter();

        databaseStatisticCounter.averageMetricCounting(true);
    }
}
