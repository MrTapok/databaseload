package org.spbu.dao;

/**
 * Class for keeping raw data on user
 */

public class UserDAO {
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    private boolean sex;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public boolean getSex() {
        return sex;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public UserDAO(int id, String name, String surname, String patronymic, boolean sex)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.sex = sex;
    }
}
