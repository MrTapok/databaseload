package org.spbu.service;

import org.spbu.provider.DataProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private DataProvider dataProviderInput;
    private DataProvider dataProviderOutput;
    ResultSet resultSet;

    public ArrayList<String> checkWriting(int method, char[] ch1, ArrayList<String> strings){
        ArrayList<String> answ = new ArrayList<String>();
        double metric = 1000;
        double temp = 0;
        switch (method){
            case 1:
                for (int i = 0; i < strings.size(); i++) {
                    temp = FuzzySearch.methodJaro(ch1, strings.get(i));
                    if(temp == metric){
                        answ.add(strings.get(i));
                    }
                    if(temp<metric){
                        answ.removeAll(answ);
                        metric = temp;
                        answ.add(strings.get(i));
                    }
                }
                return answ;
            case 2:
                for (int i = 0; i < strings.size(); i++) {
                    temp = FuzzySearch.methodJaroWinkler(ch1, strings.get(i), 0.1);
                    if(temp == metric){
                        answ.add(strings.get(i));
                    }
                    if(temp<metric){
                        answ.removeAll(answ);
                        metric = temp;
                        answ.add(strings.get(i));
                    }
                }
                return answ;
            case 3:
                for (int i = 0; i < strings.size(); i++) {
                    temp = FuzzySearch.methodWagnerFisherL(ch1, strings.get(i));
                    if(temp == metric){
                        answ.add(strings.get(i));
                    }
                    if(temp<metric){
                        answ.removeAll(answ);
                        metric = temp;
                        answ.add(strings.get(i));
                    }
                }
                return answ;
            case 4:
                for (int i = 0; i < strings.size(); i++) {
                    temp = FuzzySearch.methodWagnerFisherLTwoRows(ch1, strings.get(i));
                    if(temp == metric){
                        answ.add(strings.get(i));
                    }
                    if(temp<metric){
                        answ.removeAll(answ);
                        metric = temp;
                        answ.add(strings.get(i));
                    }
                }
                return answ;
            case 5:
                for (int i = 0; i < strings.size(); i++) {
                    temp = FuzzySearch.methodWagnerFisherDL(ch1, strings.get(i));
                    if(temp == metric){
                        answ.add(strings.get(i));
                    }
                    if(temp<metric){
                        answ.removeAll(answ);
                        metric = temp;
                        answ.add(strings.get(i));
                    }
                }
                return answ;
        }
        return answ;
    }

    public void processDB(int method, ArrayList<String> m_names, ArrayList<String> m_surnames, ArrayList<String> m_patronymics, ArrayList<String> f_names, ArrayList<String> f_surnames, ArrayList<String> f_patronymics) throws SQLException {
        dataProviderInput = new DataProvider();
        dataProviderOutput = new DataProvider();
        dataProviderInput.getConnection();
        dataProviderOutput.getConnection();
        resultSet = dataProviderInput.getAllInvalidUsers();
        resultSet.next();
        System.out.println("heer");
        int id = 0;
        char[] name;
        char[] surname;
        char[] patronymic;
        String a_name = "";
        String a_surname = "";
        String a_patronymic = "";
        boolean sex = false;
        while(!resultSet.isAfterLast()){
            id = resultSet.getInt("id");
            name = Convertor.convertStringToChar(resultSet.getString("name"));
            surname = Convertor.convertStringToChar(resultSet.getString("surname"));
            patronymic = Convertor.convertStringToChar(resultSet.getString("patronymic"));
            sex = resultSet.getBoolean("sex");
            if(sex){
                a_name = checkWriting(method, name, m_names).get(0);
                a_surname = checkWriting(method, surname, m_surnames).get(0);
                a_patronymic = checkWriting(method, patronymic, m_patronymics).get(0);
            }
            else{
                a_name = checkWriting(method, name, f_names).get(0);
                a_surname = checkWriting(method, surname, f_surnames).get(0);
                a_patronymic = checkWriting(method, patronymic, f_patronymics).get(0);
            }
            resultSet.next();
            dataProviderOutput.insertValidUser(id, a_name, a_surname, a_patronymic, sex);
        }
    }

    public double compareDB() throws SQLException {
        dataProviderInput = new DataProvider();
        dataProviderOutput = new DataProvider();
        dataProviderInput.getConnection();
        dataProviderOutput.getConnection();

        double true_counter = 0;
        double counter = 0;

        System.out.println("get");


        ResultSet resultSet1 = dataProviderInput.getAllUsers();
        ResultSet resultSet2 = dataProviderOutput.getAllValidUsers();

        resultSet1.next();
        resultSet2.next();

        String a_name = "";
        String a_surname = "";
        String a_patronymic = "";

        String b_name = "";
        String b_surname = "";
        String b_patronymic = "";

        while (!resultSet1.isAfterLast()){
            a_name = resultSet1.getString("name");
            a_surname = resultSet1.getString("surname");
            a_patronymic = resultSet1.getString("patronymic");

            System.out.println(a_name + " " + a_surname + " " + a_patronymic);

            b_name = resultSet2.getString("name");
            b_surname = resultSet2.getString("surname");
            b_patronymic = resultSet2.getString("patronymic");

            System.out.println(b_name + " " + b_surname + " " + b_patronymic);

            if((a_name.equals(b_name))&(a_surname.equals(b_surname))&(a_patronymic.equals(b_patronymic))){
                true_counter++;

            }
            counter ++;
            resultSet1.next();
            resultSet2.next();
        }
        System.out.println(true_counter + " " + counter);
        return true_counter/counter;
    }

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
