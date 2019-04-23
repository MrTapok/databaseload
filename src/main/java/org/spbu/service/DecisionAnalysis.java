package org.spbu.service;

import org.spbu.dao.UserMetricsDAO;
import org.spbu.provider.DataProvider;
//import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DecisionAnalysis {

    private DataProvider dataProviderInput;
    private DataProvider dataProviderOutput;
    private ResultSet resultSet;

    public void allDatabaseConsistencyCounting() throws SQLException {

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();

        resultSet = dataProviderInput.getAllUserData();
        resultSet.next();
        int user_id = 0;

        int nameVowelCount = 0;
        int nameConsonantCount = 0;
        int nameSignCount = 0;

        int surnameVowelCount = 0;
        int surnameConsonantCount = 0;
        int surnameSignCount = 0;

        int patronymicVowelCount = 0;
        int patronymicConsonantCount = 0;
        int patronymicSignCount = 0;

        boolean nameDoubleLetter = false;
        boolean surnameDoubleLetter = false;
        boolean patronymicDoubleLetter = false;

        boolean multipleLetter = false;

        while(!resultSet.isAfterLast()) {
            try {
                user_id = resultSet.getInt("user_id");

                nameVowelCount = resultSet.getInt("name_vowel_count");
                nameConsonantCount = resultSet.getInt("name_consonant_count");
                nameSignCount = resultSet.getInt("name_sign_count");

                surnameVowelCount = resultSet.getInt("surname_vowel_count");
                surnameConsonantCount = resultSet.getInt("surname_consonant_count");
                surnameSignCount = resultSet.getInt("surname_sign_count");

                patronymicVowelCount = resultSet.getInt("patronymic_vowel_count");
                patronymicConsonantCount = resultSet.getInt("patronymic_consonant_count");
                patronymicSignCount = resultSet.getInt("patronymic_sign_count");

                nameDoubleLetter = resultSet.getBoolean("name_dl_containing");
                surnameDoubleLetter = resultSet.getBoolean("surname_dl_containing");
                patronymicDoubleLetter = resultSet.getBoolean("patronymic_dl_containing");

                multipleLetter = resultSet.getBoolean("multiple_letter_containing");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            UserMetricsDAO userMetricsDAO = new UserMetricsDAO(user_id,
                    nameVowelCount,
                    nameConsonantCount,
                    nameSignCount,
                    surnameVowelCount,
                    surnameConsonantCount,
                    surnameSignCount,
                    patronymicVowelCount,
                    patronymicConsonantCount,
                    patronymicSignCount,
                    nameDoubleLetter,
                    surnameDoubleLetter,
                    patronymicDoubleLetter,
                    multipleLetter);

            dataProviderOutput.countUserConsistency(userMetricsDAO);
            resultSet.next();
        }
    }
}
