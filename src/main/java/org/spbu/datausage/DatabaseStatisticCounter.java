package org.spbu.datausage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

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

    public void averageLetterCount() throws SQLException {
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

    }

}
