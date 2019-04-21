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

    public void addUser(){
        Scanner in = new Scanner(System.in);

        dataProviderInput = new DataProvider();
        dataProviderInput.getConnection();

        String name;
        String surname;
        String patronymic;
        boolean sex;

        boolean flag = true;

        System.out.println("Приветствую");
        System.out.println("Введите пожалуйста ваши ФИО и пол. ФИО вводятся кириллицей, пол обозначается М или Ж");

        while(true) {
            System.out.print("Введите свое имя (по окончании ввода нажмите Enter): ");
            name = in.nextLine();
            for (int i = 0; i < name.length(); i++) {
                if((vowels.indexOf(name.charAt(i))==-1)&&(consonant.indexOf(name.charAt(i))==-1)&&(signs.indexOf(name.charAt(i))==-1)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                break;
            }
            else {
                System.out.println("Неверный формат. Имя обязано содержать символы только из кириллицы и/или дефис.");
            }
        }

        while(true) {
            System.out.print("Введите свою фамилию (по окончании ввода нажмите Enter): ");
            surname = in.nextLine();
            for (int i = 0; i < surname.length(); i++) {
                if((vowels.indexOf(surname.charAt(i))==-1)&&(consonant.indexOf(surname.charAt(i))==-1)&&(signs.indexOf(surname.charAt(i))==-1)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                break;
            }
            else {
                System.out.println("Неверный формат. Фамилия обязана содержать символы только из кириллицы и/или дефис.");
            }
        }

        while(true) {
            System.out.print("Введите свое отчество (по окончании ввода нажмите Enter): ");
            patronymic = in.nextLine();
            for (int i = 0; i < patronymic.length(); i++) {
                if((vowels.indexOf(patronymic.charAt(i))==-1)&&(consonant.indexOf(patronymic.charAt(i))==-1)&&(signs.indexOf(patronymic.charAt(i))==-1)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                break;
            }
            else {
                System.out.println("Неверный формат. Отчество обязано содержать символы только из кириллицы и/или дефис.");
            }
        }

        while (true){
            String temp;
            System.out.println("Введите свой пол (М - мужской, Ж - женский)");
            temp = in.nextLine();

            if((temp.length()>1)||((temp.equals("М"))&&(temp.equals("Ж")))){
                System.out.println(temp.length());
                System.out.println("Неверный формат. Повторите ввод.");
            }
            else {
                if(temp.equals("М")){
                    sex = true;
                    break;
                }
                else{
                    sex = false;
                    break;
                }
            }
        }

        dataProviderInput.insertNewUser(name,surname,patronymic,sex);

    }
}
