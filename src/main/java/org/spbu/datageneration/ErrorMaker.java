package org.spbu.datageneration;

import org.spbu.service.Convertor;

import java.util.Random;

public class ErrorMaker {
    private static String vowels = "УЕЫАОЭЯИЮЁ";
    private static String consonant = "ЙЦКНГШЩЗХФВПРЛДЖЧСМТБ";
    private static String signs = "ЬЪ-";

    public static String deleteFromString(String string, int amount){
        if(amount == 0){
            return string;
        }
        Random random = new Random();
        char[] temp = Convertor.convertStringToChar(string);
        int index = random.nextInt(string.length());
        int phase = 0;
        char[] answ = new char[string.length() - amount];
        for (int i = 0; i < temp.length; i++) {
            if(i == index){
                phase++;
                continue;
            }
            answ[i - phase] = temp[i];
        }
        return Convertor.convertCharToSting(answ);
    }

    public static String addToString(String string, int amount){
        if(amount == 0){
            return string;
        }
        Random random = new Random();
        char[] temp = Convertor.convertStringToChar(string);
        int index = random.nextInt(string.length());
        int phase = 0;
        int randomInt = random.nextInt(2);
        char[] answ = new char[string.length() + amount];
        for (int i = 0; i < temp.length; i++) {
            answ[i+phase] = temp[i];
            if(i == index){
                phase++;
                if(randomInt == 0) {
                    answ[i+phase] = vowels.charAt(random.nextInt(vowels.length()));
                }
                else{
                    answ[i+phase] = consonant.charAt(random.nextInt(consonant.length()));
                }
            }
        }
        return Convertor.convertCharToSting(answ);
    }

    public static String transposeInString(String string, int amount){
        if(amount == 0){
            return string;
        }
        Random random = new Random();
        char[] temp = Convertor.convertStringToChar(string);
        int index = random.nextInt(string.length()-1);
        char tempc;
        tempc = temp[index];
        temp[index] = temp[index+1];
        temp[index+1] = tempc;
        return Convertor.convertCharToSting(temp);
    }

    public static String changeInString(String string, int amount){
        if(amount == 0){
            return string;
        }
        Random random = new Random();
        char[] temp = Convertor.convertStringToChar(string);
        int[] index = new int[amount];
        for (int i = 0; i < amount; i++) {
            index[i] = random.nextInt(string.length() - 1);
        }
        for (int i = 0; i < amount; i++) {
            int randomInt = random.nextInt(2);
            if(randomInt == 0){
                temp[index[i]] = vowels.charAt(random.nextInt(vowels.length()));
            }
            else{
                temp[index[i]] = consonant.charAt(random.nextInt(consonant.length()));
            }
        }
        return Convertor.convertCharToSting(temp);
    }

    public static String deformString(String string){
        Random random = new Random();

        int randomInt1;

        randomInt1 = random.nextInt(10);


        switch (randomInt1){
            case 0:
                string = transposeInString(string, 1);
                string = transposeInString(string, 1);
                break;
            case 1:
                string = changeInString(string, 1);
                string = changeInString(string, 1);
                break;
            case 2:
                string = deleteFromString(string, 1);
                string = deleteFromString(string, 1);
                break;
            case 3:
                string = addToString(string, 1);
                string = addToString(string, 1);
                break;
            case 4:
                string = transposeInString(string, 1);
                string = changeInString(string, 1);
                break;
            case 5:
                string = transposeInString(string, 1);
                string = deleteFromString(string, 1);
                break;
            case 6:
                string = transposeInString(string, 1);
                string = addToString(string, 1);
                break;
            case 7:
                string = changeInString(string, 1);
                string = deleteFromString(string, 1);
                break;
            case 8:
                string = changeInString(string, 1);
                string = addToString(string, 1);
            case 9:
                string = deleteFromString(string, 1);
                string = addToString(string, 1);
                break;
        }
        return string;
    }

    public static String randomString(){
        String answ = "";
        Random random = new Random();

        int length = random.nextInt(20) + 1;
        int temp = 0;

        String[] answarr = new String[length];

        for (int i = 0; i < length; i++) {
            temp = random.nextInt(8);
            if(temp == 0 | temp == 2 | temp == 4 | temp == 6){
                answarr[i] = Character.toString(vowels.charAt(random.nextInt(vowels.length())));
            }
            else{
                if(temp == 1 | temp == 3 | temp == 5 | temp == 7){
                    answarr[i] = Character.toString(consonant.charAt(random.nextInt(consonant.length())));
                }
                else {
                    answarr[i] = Character.toString(signs.charAt(random.nextInt(signs.length())));
                }
            }
        }

        for (int i = 0; i < length; i++) {
            answ = answ + answarr[i];
        }

        return answ;
    }
}
