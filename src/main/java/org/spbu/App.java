package org.spbu;

import org.spbu.datausage.DatabaseFiller;

public class App {

    public static void main(String[] args) {
        DatabaseFiller databaseFiller = new DatabaseFiller();
        databaseFiller.generateData();
    }
}
