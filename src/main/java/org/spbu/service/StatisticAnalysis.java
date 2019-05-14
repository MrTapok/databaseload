package org.spbu.service;

import org.spbu.provider.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class StatisticAnalysis {

    private DataProvider dataProviderInput;
    private DataProvider dataProviderOutput;
    private ResultSet resultSet;

    public double[][] boundsCounting() throws SQLException {

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();

        resultSet = dataProviderInput.getAllUserData();
        resultSet.next();

        int nameVowelCount = 0;
        int nameConsonantCount = 0;
        int nameSignCount = 0;

        int surnameVowelCount = 0;
        int surnameConsonantCount = 0;
        int surnameSignCount = 0;

        int patronymicVowelCount = 0;
        int patronymicConsonantCount = 0;
        int patronymicSignCount = 0;

        int nameVowelsInRow = 0;
        int nameConsonantInRow = 0;
        int surnameVowelsInRow = 0;
        int surnameConsonantInRow = 0;
        int patronymicVowelsInRow = 0;
        int patronymicConsonantInRow = 0;

        ArrayList<Integer> nameVowelCountList = new ArrayList<Integer>();
        ArrayList<Integer> nameConsonantCountList = new ArrayList<Integer>();
        ArrayList<Integer> nameSignCountList = new ArrayList<Integer>();

        ArrayList<Integer> surnameVowelCountList = new ArrayList<Integer>();
        ArrayList<Integer> surnameConsonantCountList = new ArrayList<Integer>();
        ArrayList<Integer> surnameSignCountList = new ArrayList<Integer>();

        ArrayList<Integer> patronymicVowelCountList = new ArrayList<Integer>();
        ArrayList<Integer> patronymicConsonantCountList = new ArrayList<Integer>();
        ArrayList<Integer> patronymicSignCountList = new ArrayList<Integer>();

        ArrayList<Integer> nameVowelsInRowList = new ArrayList<Integer>();
        ArrayList<Integer> nameConsonantInRowList = new ArrayList<Integer>();
        ArrayList<Integer> surnameVowelsInRowList = new ArrayList<Integer>();
        ArrayList<Integer> surnameConsonantInRowList = new ArrayList<Integer>();
        ArrayList<Integer> patronymicVowelsInRowList = new ArrayList<Integer>();
        ArrayList<Integer> patronymicConsonantInRowList = new ArrayList<Integer>();

        ArrayList<Integer> nameVowelToConsonantRatioList = new ArrayList<Integer>();
        ArrayList<Integer> surnameVowelToConsonantRatioList = new ArrayList<Integer>();
        ArrayList<Integer> patronymicVowelToConsonantRatioList = new ArrayList<Integer>();

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

                nameVowelsInRow = resultSet.getInt("name_vowels_in_row");
                nameConsonantInRow = resultSet.getInt("name_consonant_in_row");
                surnameVowelsInRow = resultSet.getInt("surname_vowels_in_row");
                surnameConsonantInRow = resultSet.getInt("surname_consonant_in_row");
                patronymicVowelsInRow = resultSet.getInt("patronymic_vowels_in_row");
                patronymicConsonantInRow = resultSet.getInt("patronymic_consonant_in_row");

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

            nameVowelsInRowList.add(nameVowelsInRow);
            nameConsonantInRowList.add(nameConsonantInRow);
            surnameVowelsInRowList.add(surnameVowelsInRow);
            surnameConsonantInRowList.add(surnameConsonantInRow);
            patronymicVowelsInRowList.add(patronymicVowelsInRow);
            patronymicConsonantInRowList.add(patronymicConsonantInRow);

            nameVowelToConsonantRatioList.add(nameVowelCount - nameConsonantCount);
            surnameVowelToConsonantRatioList.add(surnameVowelCount - surnameConsonantCount);
            patronymicVowelToConsonantRatioList.add(patronymicVowelCount - patronymicConsonantCount);

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

        Collections.sort(nameVowelsInRowList);
        Collections.sort(nameConsonantInRowList);
        Collections.sort(surnameVowelsInRowList);
        Collections.sort(surnameConsonantInRowList);
        Collections.sort(patronymicVowelsInRowList);
        Collections.sort(patronymicConsonantInRowList);

        Collections.sort(nameVowelToConsonantRatioList);
        Collections.sort(surnameVowelToConsonantRatioList);
        Collections.sort(patronymicVowelToConsonantRatioList);

        double[][] answ = new double[18][2];

        //------


        answ[0] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(nameVowelCountList));
        answ[1] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(nameConsonantCountList));
        answ[2] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(nameSignCountList));

        answ[3] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(surnameVowelCountList));
        answ[4] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(surnameConsonantCountList));
        answ[5] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(surnameSignCountList));

        answ[6] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(patronymicVowelCountList));
        answ[7] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(patronymicConsonantCountList));
        answ[8] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(patronymicSignCountList));

        answ[9] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(nameVowelsInRowList));
        answ[10] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(nameConsonantInRowList));
        answ[11] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(surnameVowelsInRowList));
        answ[12] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(surnameConsonantInRowList));
        answ[13] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(patronymicVowelsInRowList));
        answ[14] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(patronymicConsonantInRowList));

        answ[15] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(nameVowelToConsonantRatioList));
        answ[16] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(surnameVowelToConsonantRatioList));
        answ[17] = BasicAnalysis.boundCount(BasicAnalysis.getPercentiles(patronymicVowelToConsonantRatioList));

        return answ;
    }

    public void consistencyCount(double[][] bounds) throws SQLException {
        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderOutput = new DataProvider();
        dataProviderOutput.getConnection();

        resultSet = dataProviderInput.getAllUserData();
        resultSet.next();

        //System.out.println(bounds);

        int id = 0;

        int nameVowelCount = 0;
        int nameConsonantCount = 0;
        int nameSignCount = 0;

        int surnameVowelCount = 0;
        int surnameConsonantCount = 0;
        int surnameSignCount = 0;

        int patronymicVowelCount = 0;
        int patronymicConsonantCount = 0;
        int patronymicSignCount = 0;

        int nameVowelsInRow = 0;
        int nameConsonantInRow = 0;
        int surnameVowelsInRow = 0;
        int surnameConsonantInRow = 0;
        int patronymicVowelsInRow = 0;
        int patronymicConsonantInRow = 0;

        int n = 0;
        int s = 0;
        int p = 0;

        int[] temp = new int[18];

        while(!resultSet.isAfterLast())
        {
            try {

                int count = 0;

                id = resultSet.getInt("user_id");

                temp[0] = resultSet.getInt("name_vowel_count");
                temp[1] = resultSet.getInt("name_consonant_count");
                temp[2] = resultSet.getInt("name_sign_count");

                temp[3] = resultSet.getInt("surname_vowel_count");
                temp[4] = resultSet.getInt("surname_consonant_count");
                temp[5] = resultSet.getInt("surname_sign_count");

                temp[6] = resultSet.getInt("patronymic_vowel_count");
                temp[7] = resultSet.getInt("patronymic_consonant_count");
                temp[8] = resultSet.getInt("patronymic_sign_count");

                temp[9] = resultSet.getInt("name_vowels_in_row");
                temp[10] = resultSet.getInt("name_consonant_in_row");
                temp[11] = resultSet.getInt("surname_vowels_in_row");
                temp[12] = resultSet.getInt("surname_consonant_in_row");
                temp[13] = resultSet.getInt("patronymic_vowels_in_row");
                temp[14] = resultSet.getInt("patronymic_consonant_in_row");

                temp[15] = temp[0] - temp[1];
                temp[16] = temp[3] - temp[4];
                temp[17] = temp[6] - temp[7];

                //System.out.println(temp[17]);

                for (int i = 0; i < 18; i++) {
                    if(!((temp[i]>=bounds[i][0])&(temp[i]<=bounds[i][1]))){
                        count++;
                    }
                }

                dataProviderOutput.updateStatConsist(id, count);

            }
            catch (SQLException e) {
                //e.printStackTrace();
            }
            resultSet.next();
        }
    }
}
