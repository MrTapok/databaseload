package org.spbu.dao;

import org.spbu.calculation.LetterCounter;

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

    private boolean nameDoubleLetter;
    private boolean surnameDoubleLetter;
    private boolean patronymicDoubleLetter;

    public UserMetricsDAO(int user_id, int nameVowelCount, int nameConsonantCount, int nameSignCount, int surnameVowelCount, int surnameConsonantCount, int surnameSignCount, int patronymicVowelCount, int patronymicConsonantCount, int patronymicSignCount, boolean nameDoubleLetter, boolean surnameDoubleLetter, boolean patronymicDoubleLetter) {
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
        this.nameDoubleLetter = nameDoubleLetter;
        this.surnameDoubleLetter = surnameDoubleLetter;
        this.patronymicDoubleLetter = patronymicDoubleLetter;
    }

    public UserMetricsDAO(UserDAO user){

        this.user_id = user.getId();

        this.nameVowelCount = LetterCounter.vowelCount(user.getName());
        this.nameConsonantCount = LetterCounter.consonantCount(user.getName());
        this.nameSignCount = LetterCounter.signCount(user.getName());

        this.surnameVowelCount = LetterCounter.vowelCount(user.getSurname());
        this.surnameConsonantCount = LetterCounter.consonantCount(user.getSurname());
        this.surnameSignCount = LetterCounter.signCount(user.getSurname());

        this.patronymicVowelCount = LetterCounter.vowelCount(user.getPatronymic());
        this.patronymicConsonantCount = LetterCounter.consonantCount(user.getPatronymic());
        this.patronymicSignCount = LetterCounter.signCount(user.getPatronymic());

        this.nameDoubleLetter = LetterCounter.doubleLetterContaining(user.getName());
        this.surnameDoubleLetter = LetterCounter.doubleLetterContaining(user.getSurname());
        this.patronymicDoubleLetter = LetterCounter.doubleLetterContaining(user.getPatronymic());

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

    public boolean isNameDoubleLetter() {
        return nameDoubleLetter;
    }

    public boolean isSurnameDoubleLetter() {
        return surnameDoubleLetter;
    }

    public boolean isPatronymicDoubleLetter() {
        return patronymicDoubleLetter;
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

    public void setNameDoubleLetter(boolean nameDoubleLetter) {
        this.nameDoubleLetter = nameDoubleLetter;
    }

    public void setSurnameDoubleLetter(boolean surnameDoubleLetter) {
        this.surnameDoubleLetter = surnameDoubleLetter;
    }

    public void setPatronymicDoubleLetter(boolean patronymicDoubleLetter) {
        this.patronymicDoubleLetter = patronymicDoubleLetter;
    }
}
