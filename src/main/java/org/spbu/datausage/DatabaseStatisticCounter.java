package org.spbu.datausage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileWriter;
import java.sql.Timestamp;

import org.spbu.counter.LetterCounter;
import org.spbu.datastorage.UserData;

/**
 * Class containing methods for statistic count
 */

public class DatabaseStatisticCounter {

    private UserData userData;
    private DataGetter dataGetter;
    private ResultSet resultSet;

    public void averageLetterCount_idVersion()
    {
        dataGetter = new DataGetter();
        dataGetter.getConnection();

        double[] averageNameLetters = {0,0,0};
        double[] averageSurnameLetters = {0,0,0};
        double[] averagePatronymicLetters = {0,0,0};

        for (int i = 1; i < 50000; i++) {
            userData = dataGetter.getUserByID(i);

            averageNameLetters[0] += LetterCounter.vowelCount(userData.getName());
            averageNameLetters[1] += LetterCounter.consonantCount(userData.getName());
            averageNameLetters[2] += LetterCounter.signCount(userData.getName());

            averageSurnameLetters[0] += LetterCounter.vowelCount(userData.getSurname());
            averageSurnameLetters[1] += LetterCounter.consonantCount(userData.getSurname());
            averageSurnameLetters[2] += LetterCounter.signCount(userData.getSurname());

            averagePatronymicLetters[0] += LetterCounter.vowelCount(userData.getPatronymic());
            averagePatronymicLetters[1] += LetterCounter.consonantCount(userData.getPatronymic());
            averagePatronymicLetters[2] += LetterCounter.signCount(userData.getPatronymic());
        }

        for (int i = 0; i < 3; i++) {
            averageNameLetters[i] = averageNameLetters[i]/50000;
            averageSurnameLetters[i] = averageSurnameLetters[i]/50000;
            averagePatronymicLetters[i] = averagePatronymicLetters[i]/50000;
        }

        System.out.println(averageNameLetters[0] + " " + averageNameLetters[1] + " " + averageNameLetters[2]);
        System.out.println(averageSurnameLetters[0] + " " + averageSurnameLetters[1] + " " + averageSurnameLetters[2]);
        System.out.println(averagePatronymicLetters[0] + " " + averagePatronymicLetters[1] + " " + averagePatronymicLetters[2]);

    }

    public void averageLetterCount() throws SQLException, IOException {

        FileWriter fileWriter = new FileWriter ("C://Users/Бгатов Михаил/databaseload/src/main/resources/numbersoutput/overalldata.txt",true);
        dataGetter = new DataGetter();
        dataGetter.getConnection();

        double[] averageNameLetters = {0,0,0};
        double[] averageSurnameLetters = {0,0,0};
        double[] averagePatronymicLetters = {0,0,0};

        double divider = 1;

        resultSet = dataGetter.getAllData();
        resultSet.next();
        String name = null;
        String surname = null;
        String patronymic = null;

        while(!resultSet.isAfterLast())
        {
            try {
                name = resultSet.getString("name");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            try {
                surname = resultSet.getString("surname");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            try {
                patronymic = resultSet.getString("fathname");
            } catch (SQLException e) {
                //e.printStackTrace();
            }

            averageNameLetters[0] += LetterCounter.vowelCount(name);
            averageNameLetters[1] += LetterCounter.consonantCount(name);
            averageNameLetters[2] += LetterCounter.signCount(name);

            averageSurnameLetters[0] += LetterCounter.vowelCount(surname);
            averageSurnameLetters[1] += LetterCounter.consonantCount(surname);
            averageSurnameLetters[2] += LetterCounter.signCount(surname);

            averagePatronymicLetters[0] += LetterCounter.vowelCount(patronymic);
            averagePatronymicLetters[1] += LetterCounter.consonantCount(patronymic);
            averagePatronymicLetters[2] += LetterCounter.signCount(patronymic);

            divider++;
            resultSet.next();
        }

        for (int i = 0; i < 3; i++) {
            averageNameLetters[i] = averageNameLetters[i]/divider;
            averageSurnameLetters[i] = averageSurnameLetters[i]/divider;
            averagePatronymicLetters[i] = averagePatronymicLetters[i]/divider;
        }

        System.out.println(averageNameLetters[0] + " " + averageNameLetters[1] + " " + averageNameLetters[2]);
        System.out.println(averageSurnameLetters[0] + " " + averageSurnameLetters[1] + " " + averageSurnameLetters[2]);
        System.out.println(averagePatronymicLetters[0] + " " + averagePatronymicLetters[1] + " " + averagePatronymicLetters[2]);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        fileWriter.write(String.valueOf(timestamp) + "\n");

        fileWriter.write("Average number of vowels in names: " + averageNameLetters[0] + "\n");
        fileWriter.write("Average number of vowels in surnames: " + averageSurnameLetters[0] + "\n");
        fileWriter.write("Average number of vowels in patronymics: " + averagePatronymicLetters[0] + "\n");

        fileWriter.write("Average number of consonants in names: " + averageNameLetters[1] + "\n");
        fileWriter.write("Average number of consonants in surnames: " + averageSurnameLetters[1] + "\n");
        fileWriter.write("Average number of consonants in patronymics: " + averagePatronymicLetters[1] + "\n");

        fileWriter.write("Average number of signs in names: " + averageNameLetters[2] + "\n");
        fileWriter.write("Average number of signs in surnames: " + averageSurnameLetters[2] + "\n");
        fileWriter.write("Average number of signs in patronymics: " + averagePatronymicLetters[2] + "\n");

        fileWriter.flush();
        fileWriter.close();


    }

    public void doubleLetterStatistic () throws SQLException, IOException {
        FileWriter fileWriter = new FileWriter ("C://Users/Бгатов Михаил/databaseload/src/main/resources/numbersoutput/overalldata.txt",true);

        dataGetter = new DataGetter();
        dataGetter.getConnection();

        int[] singleDoubleLetterContaining = {0,0,0}; //name - surname - patronymic
        int[] doubleDoubleLetterContaining = {0,0,0}; //name:surname - surname:patronymic - name:patronymic
        int tripleDoubleLetterContaining = 0; //name:surname:patronymic

        resultSet = dataGetter.getAllData();
        resultSet.next();
        String name = null;
        String surname = null;
        String patronymic = null;

        boolean nameFlag = false;
        boolean surnameFlag = false;
        boolean patronymicFlag = false;

        while(!resultSet.isAfterLast())
        {
            try {
                name = resultSet.getString("name");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            try {
                surname = resultSet.getString("surname");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
            try {
                patronymic = resultSet.getString("fathname");
            } catch (SQLException e) {
                //e.printStackTrace();
            }

            nameFlag = LetterCounter.doubleLetterContaining(name);
            surnameFlag = LetterCounter.doubleLetterContaining(surname);
            patronymicFlag = LetterCounter.doubleLetterContaining(patronymic);

            if(nameFlag & surnameFlag & patronymicFlag)
            {
                tripleDoubleLetterContaining++;
                resultSet.next();
                continue;
            }
            if(nameFlag & surnameFlag)
            {
                doubleDoubleLetterContaining[0]++;
                resultSet.next();
                continue;
            }
            if(nameFlag & patronymicFlag)
            {
                doubleDoubleLetterContaining[2]++;
                resultSet.next();
                continue;
            }
            if(patronymicFlag & surnameFlag)
            {
                doubleDoubleLetterContaining[1]++;
                resultSet.next();
                continue;
            }
            if(nameFlag)
            {
                singleDoubleLetterContaining[0]++;
                resultSet.next();
                continue;
            }
            if(surnameFlag)
            {
                singleDoubleLetterContaining[1]++;
                resultSet.next();
                continue;
            }
            if(patronymicFlag)
            {
                singleDoubleLetterContaining[2]++;
                resultSet.next();
                continue;
            }
            resultSet.next();
        }

        System.out.println(singleDoubleLetterContaining[0] + " " + singleDoubleLetterContaining[1] + " " + singleDoubleLetterContaining[2]);
        System.out.println(doubleDoubleLetterContaining[0] + " " + doubleDoubleLetterContaining[1] + " " + doubleDoubleLetterContaining[2]);
        System.out.println(tripleDoubleLetterContaining);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        fileWriter.write(String.valueOf(timestamp) + "\n");

        fileWriter.write("Number of people, containing double letter only in name: " + singleDoubleLetterContaining[0] + "\n");
        fileWriter.write("Number of people, containing double letter only in surname: " + singleDoubleLetterContaining[1] + "\n");
        fileWriter.write("Number of people, containing double letter only in patronymic: " + singleDoubleLetterContaining[2] + "\n");

        fileWriter.write("Number of people, containing double letter in name and surname: " + doubleDoubleLetterContaining[0] + "\n");
        fileWriter.write("Number of people, containing double letter in surname and patronymic: " + doubleDoubleLetterContaining[1] + "\n");
        fileWriter.write("Number of people, containing double letter in name and patronymic: " + doubleDoubleLetterContaining[2] + "\n");

        fileWriter.write("Number of people, containing double letter in name, surname and patronymic: " + tripleDoubleLetterContaining + "\n");

        fileWriter.flush();
        fileWriter.close();

    }



}
