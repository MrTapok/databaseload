package org.spbu.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spbu.dao.AverageMetricsDAO;
import org.spbu.dao.UserDAO;
import org.spbu.dao.UserMetricsDAO;
import org.spbu.provider.DataProvider;
//import org.springframework.stereotype.Service;

/**
 * Class containing methods for statistic count
 */

//@Service
public class DatabaseStatisticCounter {

    private DataProvider dataProviderInput;
    private DataProvider dataProviderOutput;
    private ResultSet resultSet;

    /**
     * Method, that counts average value of vowels, consonants and signs in person's ID
     * @throws SQLException
     */

    public void allDatabaseStatisticCounting(Boolean update) throws SQLException {

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();

        //List<UserMetricsDAO> metrics = new ArrayList<UserMetricsDAO>();

        resultSet = dataProviderInput.getAllUsers();
        resultSet.next();
        int id = 0;
        String name = null;
        String surname = null;
        String patronymic = null;
        boolean sex = false;

        while(!resultSet.isAfterLast())
        {
            try{
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                patronymic = resultSet.getString("fathname");
                sex = resultSet.getBoolean("sex");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            UserDAO userDAO = new UserDAO(id, name, surname, patronymic, sex);

            UserMetricsDAO userMetricsDAO = new UserMetricsDAO(userDAO);

            if(update){
                dataProviderOutput.updateUserMetrics(userMetricsDAO);
            }
            else {
                dataProviderOutput.insertUserMetrics(userMetricsDAO);
            }
            //metrics.add(userMetricsDAO);
            resultSet.next();
        }
    }

    public void averageMetricCounting(boolean update){

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();

        AverageMetricsDAO averageMetricsDAO;

        averageMetricsDAO = dataProviderInput.countAverageMetrics();

        System.out.println(averageMetricsDAO.toString());

        if(update){
            dataProviderOutput.updateAverageMetrics(averageMetricsDAO);
        }
        else {
            dataProviderOutput.insertAverageMetrics(averageMetricsDAO);
        }

    }

    public void outlinersCounting() throws SQLException {

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();

        resultSet = dataProviderInput.getAllUserData();
        resultSet.next();

        int[] nameVowelCountArray = new int[15];
        int[] nameConsonantCountArray = new int[15];
        int[] nameSignCountArray = new int[15];

        int[] surnameVowelCountArray = new int[15];
        int[] surnameConsonantCountArray = new int[15];
        int[] surnameSignCountArray = new int[15];

        int[] patronymicVowelCountArray = new int[15];
        int[] patronymicConsonantCountArray = new int[15];
        int[] patronymicSignCountArray = new int[15];

        int[] positiveNameVowelConsonantRatio = new int[10];
        int[] negativeNameVowelConsonantRatio = new int[10];

        int[] positiveSurnameVowelConsonantRatio = new int[10];
        int[] negativeSurnameVowelConsonantRatio = new int[10];

        int[] positivePatronymicVowelConsonantRatio = new int[10];
        int[] negativePatronymicVowelConsonantRatio = new int[10];


        int nameVowelCount = 0;
        int nameConsonantCount = 0;
        int nameSignCount = 0;

        int surnameVowelCount = 0;
        int surnameConsonantCount = 0;
        int surnameSignCount = 0;

        int patronymicVowelCount = 0;
        int patronymicConsonantCount = 0;
        int patronymicSignCount = 0;

        ArrayList<Integer> nameVowelCountList = new ArrayList<Integer>();
        ArrayList<Integer> nameConsonantCountList = new ArrayList<Integer>();
        ArrayList<Integer> nameSignCountList = new ArrayList<Integer>();

        ArrayList<Integer> surnameVowelCountList = new ArrayList<Integer>();
        ArrayList<Integer> surnameConsonantCountList = new ArrayList<Integer>();
        ArrayList<Integer> surnameSignCountList = new ArrayList<Integer>();

        ArrayList<Integer> patronymicVowelCountList = new ArrayList<Integer>();
        ArrayList<Integer> patronymicConsonantCountList = new ArrayList<Integer>();
        ArrayList<Integer> patronymicSignCountList = new ArrayList<Integer>();

        while(!resultSet.isAfterLast()) {
            try {
                nameVowelCount = resultSet.getInt("name_vowel_count");
                nameConsonantCount = resultSet.getInt("name_consonant_count");
                nameSignCount = resultSet.getInt("name_sign_count");

                surnameVowelCount = resultSet.getInt("surname_vowel_count");
                surnameConsonantCount = resultSet.getInt("surname_consonant_count");
                surnameSignCount = resultSet.getInt("surname_sign_count");

                patronymicVowelCount = resultSet.getInt("patronymic_vowel_count");
                patronymicConsonantCount = resultSet.getInt("patronymic_consonant_count");
                patronymicSignCount = resultSet.getInt("patronymic_sign_count");

            } catch (SQLException e) {
                e.printStackTrace();
            }

            nameVowelCountList.add(nameVowelCount);
            nameConsonantCountList.add(nameConsonantCount);
            nameSignCountList.add(nameSignCount);

            surnameVowelCountList.add(surnameVowelCount);
            surnameConsonantCountList.add(surnameConsonantCount);
            surnameSignCountList.add(surnameSignCount);

            patronymicVowelCountList.add(patronymicVowelCount);
            patronymicConsonantCountList.add(patronymicConsonantCount);
            patronymicSignCountList.add(patronymicSignCount);

            //--------

            nameVowelCountArray[nameVowelCount]++;
            nameConsonantCountArray[nameConsonantCount]++;
            nameSignCountArray[nameSignCount]++;

            surnameVowelCountArray[surnameVowelCount]++;
            surnameConsonantCountArray[surnameConsonantCount]++;
            surnameSignCountArray[surnameSignCount]++;

            patronymicVowelCountArray[patronymicVowelCount]++;
            patronymicConsonantCountArray[patronymicConsonantCount]++;
            patronymicSignCountArray[patronymicSignCount]++;

            //--------

            if(nameVowelCount-nameConsonantCount>=0){
                positiveNameVowelConsonantRatio[nameVowelCount-nameConsonantCount]++;
            }
            else{
                negativeNameVowelConsonantRatio[Math.abs(nameVowelCount-nameConsonantCount)]++;
            }

            if(surnameVowelCount-surnameConsonantCount>=0){
                positiveSurnameVowelConsonantRatio[surnameVowelCount-surnameConsonantCount]++;
            }
            else{
                negativeSurnameVowelConsonantRatio[Math.abs(surnameVowelCount-surnameConsonantCount)]++;
            }

            if(patronymicVowelCount-patronymicConsonantCount>=0){
                positivePatronymicVowelConsonantRatio[patronymicVowelCount-patronymicConsonantCount]++;
            }
            else{
                negativePatronymicVowelConsonantRatio[Math.abs(patronymicVowelCount-patronymicConsonantCount)]++;
            }

            resultSet.next();
        }

        Collections.sort(nameVowelCountList);
        Collections.sort(nameConsonantCountList);
        Collections.sort(nameSignCountList);

        Collections.sort(surnameVowelCountList);
        Collections.sort(surnameConsonantCountList);
        Collections.sort(surnameSignCountList);

        Collections.sort(patronymicVowelCountList);
        Collections.sort(patronymicConsonantCountList);
        Collections.sort(patronymicSignCountList);

        //------

        int[] perc = new int[3];

        perc = BasicAnalysis.getPercentiles(nameVowelCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);
        perc = BasicAnalysis.getPercentiles(nameConsonantCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);
        perc = BasicAnalysis.getPercentiles(nameSignCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);

        perc = BasicAnalysis.getPercentiles(surnameVowelCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);
        perc = BasicAnalysis.getPercentiles(surnameConsonantCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);
        perc = BasicAnalysis.getPercentiles(surnameSignCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);

        perc = BasicAnalysis.getPercentiles(patronymicVowelCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);
        perc = BasicAnalysis.getPercentiles(patronymicConsonantCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);
        perc = BasicAnalysis.getPercentiles(patronymicSignCountList);
        System.out.println(perc[0] + " " + perc[1] + " " + perc[2]);

        //-----

        BasicAnalysis.intArrayToString(nameVowelCountArray);
        BasicAnalysis.intArrayToString(nameConsonantCountArray);
        BasicAnalysis.intArrayToString(nameSignCountArray);

        System.out.println();

        BasicAnalysis.intArrayToString(surnameVowelCountArray);
        BasicAnalysis.intArrayToString(surnameConsonantCountArray);
        BasicAnalysis.intArrayToString(surnameSignCountArray);

        System.out.println();

        BasicAnalysis.intArrayToString(patronymicVowelCountArray);
        BasicAnalysis.intArrayToString(patronymicConsonantCountArray);
        BasicAnalysis.intArrayToString(patronymicSignCountArray);

        System.out.println();

        //-------

        BasicAnalysis.intArrayToString(positiveNameVowelConsonantRatio);
        BasicAnalysis.intArrayToString(negativeNameVowelConsonantRatio);

        System.out.println();

        BasicAnalysis.intArrayToString(positiveSurnameVowelConsonantRatio);
        BasicAnalysis.intArrayToString(negativeSurnameVowelConsonantRatio);

        System.out.println();

        BasicAnalysis.intArrayToString(positivePatronymicVowelConsonantRatio);
        BasicAnalysis.intArrayToString(negativePatronymicVowelConsonantRatio);

        System.out.println();
    }

}
