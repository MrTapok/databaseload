package org.spbu.datageneration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that grants access to names, surnames and patronymics for DB generation
 */

class DataStorage {
    private String[] femaleNames;
    private String[] femaleSurnames;
    private String[] femalePatronymics;

    private String[] maleNames;
    private String[] maleSurnames;
    private String[] malePatronymics;

    void initializeFemaleNames(){
        File file = new File("C://Users/Бгатов Михаил/databaseload/src/main/resources/fullNameData", "femaleNames.txt");
        try {
            Scanner scanner = new Scanner(file);
            femaleNames = scanner.nextLine().split( ", ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void initializeFemaleSurnames(){
        File file = new File("C://Users/Бгатов Михаил/databaseload/src/main/resources/fullNameData","femaleSurnames.txt");
        try {
            Scanner scanner = new Scanner(file);
            femaleSurnames = scanner.nextLine().split( ", ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void initializeFemalePatronymics(){
        File file = new File("C://Users/Бгатов Михаил/databaseload/src/main/resources/fullNameData","femalePatronymics.txt");
        try {
            Scanner scanner = new Scanner(file);
            femalePatronymics = scanner.nextLine().split( ", ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void initializeMaleNames(){
        File file = new File("C://Users/Бгатов Михаил/databaseload/src/main/resources/fullNameData","maleNames.txt");
        try {
            Scanner scanner = new Scanner(file);
            maleNames = scanner.nextLine().split( ", ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void initializeMaleSurnames(){
        File file = new File("C://Users/Бгатов Михаил/databaseload/src/main/resources/fullNameData","maleSurnames.txt");
        try {
            Scanner scanner = new Scanner(file);
            maleSurnames = scanner.nextLine().split( ", ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void initializeMalePatronymics(){
        File file = new File("C://Users/Бгатов Михаил/databaseload/src/main/resources/fullNameData","malePatronymics.txt");
        try {
            Scanner scanner = new Scanner(file);
            malePatronymics = scanner.nextLine().split( ", ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    String[] getFemaleNames() {
        return femaleNames;
    }

    String[] getFemaleSurnames() {
        return femaleSurnames;
    }

    String[] getFemalePatronymics() {
        return femalePatronymics;
    }

    String[] getMaleNames() {
        return maleNames;
    }

    String[] getMaleSurnames() {
        return maleSurnames;
    }

    String[] getMalePatronymics() {
        return malePatronymics;
    }
}
