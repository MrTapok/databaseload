package org.spbu.dao;

import org.spbu.service.BasicAnalysis;

public class UserMetricsDAO {

    private int user_id;

    private int nameVowelCount;
    private int nameConsonantCount;
    private int nameSignCount;

    private int surnameVowelCount;
    private int surnameConsonantCount;
    private int surnameSignCount;

    private int patronymicVowelCount;
    private int patronymicConsonantCount;
    private int patronymicSignCount;

    private int nameVowelsInRow;
    private int nameConsonantInRow;
    private int surnameVowelsInRow;
    private int surnameConsonantInRow;
    private int patronymicVowelsInRow;
    private int patronymicConsonantInRow;


    public UserMetricsDAO(int user_id,
                          int nameVowelCount,
                          int nameConsonantCount,
                          int nameSignCount,
                          int surnameVowelCount,
                          int surnameConsonantCount,
                          int surnameSignCount,
                          int patronymicVowelCount,
                          int patronymicConsonantCount,
                          int patronymicSignCount,
                          int nameVowelsInRow,
                          int nameConsonantInRow,
                          int surnameVowelsInRow,
                          int surnameConsonantInRow,
                          int patronymicVowelsInRow,
                          int patronymicConsonantInRow) {
        this.user_id = user_id;
        this.nameVowelCount = nameVowelCount;
        this.nameConsonantCount = nameConsonantCount;
        this.nameSignCount = nameSignCount;
        this.surnameVowelCount = surnameVowelCount;
        this.surnameConsonantCount = surnameConsonantCount;
        this.surnameSignCount = surnameSignCount;
        this.patronymicVowelCount = patronymicVowelCount;
        this.patronymicConsonantCount = patronymicConsonantCount;
        this.patronymicSignCount = patronymicSignCount;
        this.nameVowelsInRow = nameVowelsInRow;
        this.nameConsonantInRow = nameConsonantInRow;
        this.surnameVowelsInRow = surnameVowelsInRow;
        this.surnameConsonantInRow = surnameConsonantInRow;
        this.patronymicVowelsInRow = patronymicVowelsInRow;
        this.patronymicConsonantInRow = patronymicConsonantInRow;

    }

    public UserMetricsDAO(UserDAO user){

        this.user_id = user.getId();

        this.nameVowelCount = BasicAnalysis.vowelCount(user.getName());
        this.nameConsonantCount = BasicAnalysis.consonantCount(user.getName());
        this.nameSignCount = BasicAnalysis.signCount(user.getName());

        this.surnameVowelCount = BasicAnalysis.vowelCount(user.getSurname());
        this.surnameConsonantCount = BasicAnalysis.consonantCount(user.getSurname());
        this.surnameSignCount = BasicAnalysis.signCount(user.getSurname());

        this.patronymicVowelCount = BasicAnalysis.vowelCount(user.getPatronymic());
        this.patronymicConsonantCount = BasicAnalysis.consonantCount(user.getPatronymic());
        this.patronymicSignCount = BasicAnalysis.signCount(user.getPatronymic());

        this.nameVowelsInRow = BasicAnalysis.vowelsInRow(user.getName());
        this.nameConsonantInRow = BasicAnalysis.consonantsInRow(user.getName());
        this.surnameVowelsInRow = BasicAnalysis.vowelsInRow(user.getSurname());
        this.surnameConsonantInRow = BasicAnalysis.consonantsInRow(user.getSurname());
        this.patronymicVowelsInRow = BasicAnalysis.vowelsInRow(user.getPatronymic());
        this.patronymicConsonantInRow = BasicAnalysis.consonantsInRow(user.getPatronymic());

    }

    //Getters

    public int getUser_id() {
        return user_id;
    }

    public int getNameVowelCount() {
        return nameVowelCount;
    }

    public int getNameConsonantCount() {
        return nameConsonantCount;
    }

    public int getNameSignCount() {
        return nameSignCount;
    }

    public int getSurnameVowelCount() {
        return surnameVowelCount;
    }

    public int getSurnameConsonantCount() {
        return surnameConsonantCount;
    }

    public int getSurnameSignCount() {
        return surnameSignCount;
    }

    public int getPatronymicVowelCount() {
        return patronymicVowelCount;
    }

    public int getPatronymicConsonantCount() {
        return patronymicConsonantCount;
    }

    public int getPatronymicSignCount() {
        return patronymicSignCount;
    }

    //Setters

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setNameVowelCount(int nameVowelCount) {
        this.nameVowelCount = nameVowelCount;
    }

    public void setNameConsonantCount(int nameConsonantCount) {
        this.nameConsonantCount = nameConsonantCount;
    }

    public void setNameSignCount(int nameSignCount) {
        this.nameSignCount = nameSignCount;
    }

    public void setSurnameVowelCount(int surnameVowelCount) {
        this.surnameVowelCount = surnameVowelCount;
    }

    public void setSurnameConsonantCount(int surnameConsonantCount) {
        this.surnameConsonantCount = surnameConsonantCount;
    }

    public void setSurnameSignCount(int surnameSignCount) {
        this.surnameSignCount = surnameSignCount;
    }

    public void setPatronymicVowelCount(int patronymicVowelCount) {
        this.patronymicVowelCount = patronymicVowelCount;
    }

    public void setPatronymicConsonantCount(int patronymicConsonantCount) {
        this.patronymicConsonantCount = patronymicConsonantCount;
    }

    public void setPatronymicSignCount(int patronymicSignCount) {
        this.patronymicSignCount = patronymicSignCount;
    }

    public int getNameVowelsInRow() {
        return nameVowelsInRow;
    }

    public void setNameVowelsInRow(int nameVowelsInRow) {
        this.nameVowelsInRow = nameVowelsInRow;
    }

    public int getNameConsonantInRow() {
        return nameConsonantInRow;
    }

    public void setNameConsonantInRow(int nameConsonantInRow) {
        this.nameConsonantInRow = nameConsonantInRow;
    }

    public int getSurnameVowelsInRow() {
        return surnameVowelsInRow;
    }

    public void setSurnameVowelsInRow(int surnameVowelsInRow) {
        this.surnameVowelsInRow = surnameVowelsInRow;
    }

    public int getSurnameConsonantInRow() {
        return surnameConsonantInRow;
    }

    public void setSurnameConsonantInRow(int surnameConsonantInRow) {
        this.surnameConsonantInRow = surnameConsonantInRow;
    }

    public int getPatronymicVowelsInRow() {
        return patronymicVowelsInRow;
    }

    public void setPatronymicVowelsInRow(int patronymicVowelsInRow) {
        this.patronymicVowelsInRow = patronymicVowelsInRow;
    }

    public int getPatronymicConsonantInRow() {
        return patronymicConsonantInRow;
    }

    public void setPatronymicConsonantInRow(int patronymicConsonantInRow) {
        this.patronymicConsonantInRow = patronymicConsonantInRow;
    }
}
