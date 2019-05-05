package org.spbu.service;

import org.spbu.dao.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Convertor {

    public String[][] convertRsToStr(ResultSet resultSet1) throws SQLException {

        int size = 0;

        if (resultSet1 != null)
        {
            resultSet1.beforeFirst();
            resultSet1.last();
            size = resultSet1.getRow();
        }

        resultSet1.beforeFirst();
        int counter = 0;

        resultSet1.next();

        String[][] answ = new String[size][6];

        String string1 = "";
        String string2 = "";
        String string3 = "";
        String string4 = "";
        String string7 = "";
        boolean string5 = false;
        boolean string6 = false;

        while(!resultSet1.isAfterLast())
        {
            try{
                string1 = resultSet1.getString("id");
                string2 = resultSet1.getString("name");
                string3 = resultSet1.getString("surname");
                string4 = resultSet1.getString("fathname");
                string5 = resultSet1.getBoolean("sex");
                string6 = resultSet1.getBoolean("consistent");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            answ[counter][0] = string1;
            answ[counter][1] = string2;
            answ[counter][2] = string3;
            answ[counter][3] = string4;
            if(string5){
                answ[counter][4] = "МУЖ";
            }
            else{
                answ[counter][4] = "ЖЕН";
            }
            if(string6){
                answ[counter][5] = "ДА";
            }
            else {
                answ[counter][5] = "НЕТ";
            }
            resultSet1.next();
            counter++;
        }



        return answ;
    }

    public static char[] convertStringToChar(String string){
        char[] answ = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            answ[i] = string.charAt(i);
        }
        return answ;
    }

    public static String convertCharToSting(char[] temp){
        String answ = "";
        for (int i = 0; i < temp.length; i++) {
            answ = answ + Character.toString(temp[i]);
        }
        return answ;
    }
}
