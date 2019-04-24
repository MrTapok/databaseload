package org.spbu.service;

import org.spbu.provider.DataProvider;

import java.sql.ResultSet;
import java.util.Scanner;

public class UserService {
    private DataProvider dataProviderInput;
    private DataProvider dataProviderOutput;
    private ResultSet resultSet;

    private static String vowels = "УЕЫАОЭЯИЮЁ";
    private static String consonant = "ЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
    private static String signs = "ЬЪ-";

    public void addUser(String name, String surname, String patronymic, boolean sex){
        Scanner in = new Scanner(System.in);

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        dataProviderInput.insertNewUser(name,surname,patronymic,sex);

    }

    public ResultSet getAllUsers(){
        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();
        return dataProviderInput.getAllUsers();
    }
}
